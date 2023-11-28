package com.example.loginapp.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ExceptionType
import com.example.core.ResultWrapperUI
import com.example.core.Toast.showToast
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isUserLogedIn()) {
            findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
        }

        binding.login.addTextChangedListener {
            binding.loginContainer.error = ""
            viewModel.onLoginChanged(it.toString())
        }

        binding.password.addTextChangedListener {
            binding.passwordContainer.error = ""
            viewModel.onPasswordChanged(it.toString())
        }

        binding.enterButton.setOnClickListener {
            if (binding.login.text.isNullOrBlank()) {
                binding.loginContainer.error = "Введите логин"
            }
            if (binding.password.text.isNullOrBlank()) {
                binding.passwordContainer.error = "Введите пароль"
            }
            if ( !binding.login.text.isNullOrBlank() && !binding.password.text.isNullOrBlank()) {
                viewModel.signIn()
            }
        }

        lifecycleScope.launch {
            launch {
                viewModel.login.collectLatest { login ->
                    if (binding.login.text.toString() != login) {
                        binding.login.setText(login)
                    }
                }
            }

            launch {
                viewModel.password.collectLatest { password ->
                    if (binding.password.text.toString() != password) {
                        binding.password.setText(password)
                    }
                }
            }

            launch {
                viewModel.loginResult.collectLatest { loginResult ->
                    when (loginResult) {
                        is ResultWrapperUI.Success -> {
                            binding.loading.root.visibility = View.GONE
                            findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
                        }

                        ResultWrapperUI.Loading -> {
                            binding.login.isEnabled = false
                            binding.password.isEnabled = false
                            binding.loading.root.visibility = View.VISIBLE
                        }

                        is ResultWrapperUI.Error -> {
                            when (loginResult.exceptionType) {
                                ExceptionType.InvalidCredentialsException -> {
                                    showToast(R.string.wrong_password)
                                }
                                else -> showToast(R.string.error)
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

}