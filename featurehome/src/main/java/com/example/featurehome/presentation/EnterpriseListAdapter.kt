package com.example.featurehome.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.constants.Constants
import com.example.core.extensions.downloadImage
import com.example.datasource.model.enterprise.Enterprise
import com.example.featurehome.R

class EnterpriseListAdapter(
    private val enterprises: List<Enterprise>,
    private val onItemClickListener: (Enterprise) -> Unit
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
            onItemClickListener: (Enterprise) -> Unit
        ) {
            itemEnterpriseCountryTextView.text = enterprise.country
            itemEnterpriseTypeTextView.text =
                enterprise.enterpriseType.enterpriseTypeName
            itemEnterpriseNameTextView.text = enterprise.enterpriseName
            itemEnterpriseImageView.downloadImage(
                Constants.BASE_IMAGE_URL + enterprise.photo
            )
            itemView.setOnClickListener { onItemClickListener.invoke(enterprise) }
        }
    }
}