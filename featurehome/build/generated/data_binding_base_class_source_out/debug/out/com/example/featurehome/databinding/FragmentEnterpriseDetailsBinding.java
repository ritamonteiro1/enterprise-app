// Generated by view binder compiler. Do not edit!
package com.example.featurehome.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.featurehome.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentEnterpriseDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout enterpriseDetailsConstraintLayout;

  @NonNull
  public final ImageView enterpriseDetailsImageView;

  @NonNull
  public final ScrollView enterpriseDetailsScrollView;

  @NonNull
  public final TextView enterpriseDetailsTextView;

  @NonNull
  public final Toolbar enterpriseDetailsToolbar;

  private FragmentEnterpriseDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout enterpriseDetailsConstraintLayout,
      @NonNull ImageView enterpriseDetailsImageView,
      @NonNull ScrollView enterpriseDetailsScrollView, @NonNull TextView enterpriseDetailsTextView,
      @NonNull Toolbar enterpriseDetailsToolbar) {
    this.rootView = rootView;
    this.enterpriseDetailsConstraintLayout = enterpriseDetailsConstraintLayout;
    this.enterpriseDetailsImageView = enterpriseDetailsImageView;
    this.enterpriseDetailsScrollView = enterpriseDetailsScrollView;
    this.enterpriseDetailsTextView = enterpriseDetailsTextView;
    this.enterpriseDetailsToolbar = enterpriseDetailsToolbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentEnterpriseDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentEnterpriseDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_enterprise_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentEnterpriseDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.enterpriseDetailsConstraintLayout;
      ConstraintLayout enterpriseDetailsConstraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (enterpriseDetailsConstraintLayout == null) {
        break missingId;
      }

      id = R.id.enterpriseDetailsImageView;
      ImageView enterpriseDetailsImageView = ViewBindings.findChildViewById(rootView, id);
      if (enterpriseDetailsImageView == null) {
        break missingId;
      }

      id = R.id.enterpriseDetailsScrollView;
      ScrollView enterpriseDetailsScrollView = ViewBindings.findChildViewById(rootView, id);
      if (enterpriseDetailsScrollView == null) {
        break missingId;
      }

      id = R.id.enterpriseDetailsTextView;
      TextView enterpriseDetailsTextView = ViewBindings.findChildViewById(rootView, id);
      if (enterpriseDetailsTextView == null) {
        break missingId;
      }

      id = R.id.enterpriseDetailsToolbar;
      Toolbar enterpriseDetailsToolbar = ViewBindings.findChildViewById(rootView, id);
      if (enterpriseDetailsToolbar == null) {
        break missingId;
      }

      return new FragmentEnterpriseDetailsBinding((ConstraintLayout) rootView,
          enterpriseDetailsConstraintLayout, enterpriseDetailsImageView,
          enterpriseDetailsScrollView, enterpriseDetailsTextView, enterpriseDetailsToolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}