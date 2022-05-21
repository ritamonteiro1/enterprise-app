import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.constants.Constants
import com.example.core.extensions.createLoadingDialog
import com.example.core.extensions.showErrorDialog
import com.example.core.model.NetworkErrorException
import com.example.core.model.UnauthorizedException
import com.example.featureauth.R
import com.example.featureauth.databinding.FragmentLoginBinding
import com.example.featureauth.domain.model.EmailStatus
import com.example.featureauth.domain.model.PasswordStatus
import com.example.featureauth.presentation.LoginViewModel
import com.example.navigation.AuthBoundary
import com.example.navigation.UserTokensNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val boundary: AuthBoundary by inject()
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: FragmentLoginBinding
    private val loadingDialog by lazy { activity?.createLoadingDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            viewModel.doLogin(
                email = binding.loginEmailEditText.text?.toString().orEmpty(),
                password = binding.loginPasswordEditText.text?.toString().orEmpty()
            )
        }
    }

    private fun setupObservers() {
        viewModel.isValidUserEmail.observe(viewLifecycleOwner) { emailStatus ->
            handleInvalidEmail(emailStatus)
        }
        viewModel.isValidUserPassword.observe(viewLifecycleOwner) { passwordStatus ->
            handleInvalidPassword(passwordStatus)
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) loadingDialog?.show()
            else loadingDialog?.dismiss()
        }
        viewModel.error.observe(viewLifecycleOwner) { exception ->
            when (exception) {
                is UnauthorizedException -> unauthorizedLogin()
                else -> {
                    val errorMessage = when (exception) {
                        is NetworkErrorException -> getString(R.string.error_connection_fail)
                        else -> getString(R.string.occurred_error)
                    }
                    activity?.showErrorDialog(errorMessage)
                }
            }
        }
        viewModel.userTokens.observe(viewLifecycleOwner) { userTokens ->
            moveToMainActivity(userTokens.accessToken, userTokens.uid, userTokens.client)
        }
    }

    private fun unauthorizedLogin() {
        binding.loginEmailTextInputLayout.error = Constants.BLANK_SPACE
        binding.loginPasswordTextInputLayout.error =
            getString(R.string.login_error_email_password)
    }

    private fun moveToMainActivity(accessToken: String, uid: String, client: String) {
        val userTokensNavigation = UserTokensNavigation(accessToken, uid, client)
        boundary.navigateToHome(activity, userTokensNavigation)
    }

    private fun handleInvalidPassword(isValidPassword: PasswordStatus) {
        when (isValidPassword) {
            PasswordStatus.VALID -> {
                binding.loginPasswordTextInputLayout.error = Constants.EMPTY
            }
            PasswordStatus.EMPTY -> {
                binding.loginPasswordTextInputLayout.error = getString(R.string.fill_the_field)
            }
            else -> {
                binding.loginPasswordTextInputLayout.error = getString(R.string.invalid_password)
            }
        }
    }

    private fun handleInvalidEmail(isValidEmail: EmailStatus) {
        when (isValidEmail) {
            EmailStatus.VALID -> {
                binding.loginEmailTextInputLayout.error = Constants.EMPTY
            }
            EmailStatus.EMPTY -> {
                binding.loginEmailTextInputLayout.error =
                    getString(R.string.fill_the_field)
            }
            else -> {
                binding.loginEmailTextInputLayout.error =
                    getString(R.string.incorrect_email)
            }
        }
    }
}