package com.example.loginapp.ui.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ResultWrapperUI
import com.example.core.toResultWrapperUI
import com.example.data.storage.sharedPreferences.Settings
import com.example.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository, private val settings: Settings) :
    ViewModel() {

    private val _login: MutableStateFlow<String> = MutableStateFlow("")
    val login = _login.asStateFlow()

    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginResult: MutableStateFlow<ResultWrapperUI<Any>?> = MutableStateFlow(null)
    val loginResult = _loginResult.asStateFlow()

    fun signIn() {
        viewModelScope.launch(Dispatchers.IO) {
            _loginResult.update {
                repository.logIn(login = login.value, password = password.value).toResultWrapperUI()
            }
        }
    }

    fun onLoginChanged(login: String) {
        _login.update {
            login
        }
    }

    fun onPasswordChanged(password: String) {
        _password.update {
            password
        }
    }

    fun isUserLogedIn(): Boolean {
        return settings.getToken() != null
    }


}