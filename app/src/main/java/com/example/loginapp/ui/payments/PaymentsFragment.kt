package com.example.loginapp.ui.payments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ResultWrapperUI
import com.example.core.Toast.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentPaymentsBinding
import com.example.loginapp.ui.adapters.PaymentsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PaymentsFragment : Fragment() {

    private lateinit var binding: FragmentPaymentsBinding
    private val viewModel: PaymentsViewModel by viewModel()

    private var paymentsAdapter = PaymentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logOut.setOnClickListener {
            viewModel.clearToken()
            findNavController().navigate(R.id.action_paymentsFragment_to_loginFragment)
        }

        lifecycleScope.launch {
            viewModel.payments.collectLatest { result ->

                when (result) {
                    is ResultWrapperUI.Success -> {
                        binding.loading.root.visibility = View.GONE
                        binding.apply {
                            paymentsRV.adapter = paymentsAdapter
                            paymentsAdapter.submitList(result.data)
                        }
                    }

                    ResultWrapperUI.Loading -> binding.loading.root.visibility = View.VISIBLE

                    is ResultWrapperUI.Error -> {
                        binding.loading.root.visibility = View.GONE
                        showToast(R.string.error)
                    }
                }
            }
        }
    }
}