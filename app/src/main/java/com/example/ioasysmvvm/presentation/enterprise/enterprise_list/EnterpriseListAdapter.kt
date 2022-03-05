package com.example.ioasysmvvm.presentation.enterprise.enterprise_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.extensions.downloadImage
import com.example.ioasysmvvm.presentation.click_listener.OnItemClickListener

class EnterpriseListAdapter(
    private val enterprises: List<Enterprise>,
    private val onItemClickListener: OnItemClickListener<Enterprise>
) : RecyclerView.Adapter<EnterpriseListAdapter.EnterpriseListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnterpriseListViewHolder {
        return EnterpriseListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_enterprise,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EnterpriseListViewHolder, position: Int) {
        holder.bind(enterprises[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return enterprises.size
    }

    inner class EnterpriseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemEnterpriseImageView: ImageView =
            itemView.findViewById(R.id.itemEnterpriseImageView)
        private val itemEnterpriseNameTextView: TextView =
            itemView.findViewById(R.id.itemEnterpriseNameTextView)
        private val itemEnterpriseTypeTextView: TextView =
            itemView.findViewById(R.id.itemEnterpriseTypeTextView)
        private val itemEnterpriseCountryTextView: TextView =
            itemView.findViewById(R.id.itemEnterpriseCountryTextView)

        fun bind(
            enterprise: Enterprise,
            onItemClickListener: OnItemClickListener<Enterprise>
        ) {
            itemEnterpriseCountryTextView.text = enterprise.country
            itemEnterpriseTypeTextView.text =
                enterprise.enterpriseType.enterpriseTypeName
            itemEnterpriseNameTextView.text = enterprise.enterpriseName
            itemEnterpriseImageView.downloadImage(
                Constants.BASE_IMAGE_URL + enterprise.photo
            )
            itemView.setOnClickListener { onItemClickListener.onClick(enterprise) }
        }
    }
}