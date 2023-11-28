package com.example.loginapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Payments
import com.example.loginapp.databinding.PaymentItemBinding
import java.time.format.DateTimeFormatter

class PaymentsAdapter : ListAdapter<Payments, PaymentsAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PaymentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.payment = getItem(position)
    }

    class ViewHolder(private var binding: PaymentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var payment: Payments? = null
            set(value) {
                value?.let { newValue ->
                    field = newValue

                    binding.apply {
                        title.text = newValue.title
                        dataCreated.text =
                            newValue.created.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                        amount.text = newValue.amount

                        if (newValue.amount.isNullOrEmpty()) {
                            amount.visibility = View.GONE
                        } else amount.visibility = View.VISIBLE

                        if (newValue.title.isEmpty()) {
                            title.visibility = View.GONE
                        } else title.visibility = View.VISIBLE

                    }
                }
            }

    }

    companion object {

        var diffUtil = object : DiffUtil.ItemCallback<Payments>() {
            override fun areItemsTheSame(oldItem: Payments, newItem: Payments): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Payments, newItem: Payments): Boolean {
                return oldItem == newItem
            }
        }

    }


}