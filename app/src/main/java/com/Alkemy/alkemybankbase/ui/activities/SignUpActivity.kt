package com.Alkemy.alkemybankbase.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.Alkemy.alkemybankbase.databinding.ActivitySignUpBinding
import com.Alkemy.alkemybankbase.presentation.SignUpViewModel
import com.Alkemy.alkemybankbase.utils.afterTextChanged
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var firebaseAnalytics : FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setuplistener()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

    }


    private fun setupObservers() {
        viewModel.isFormValidLiveData.observe(this) {
            // enable or disable button
            binding.btnSignUp.isEnabled = it
        }
        viewModel.confirmPasswordErrorResourceIdLiveData.observe(this) { resId ->
            //show confirm password error
            binding.etConfirmPassword.error = getString(resId)
        }
        viewModel.emailErrorResourceIdLiveData.observe(this) { resId ->
            //show email error
            binding.etEmail.error = getString(resId)
        }
        viewModel.passwordErrorResourceIdLiveData.observe(this) { resId ->
            //show password error
            binding.etPassword.error = getString(resId)
        }
    }

    private fun setuplistener() {
        with(binding) {
            btnSignUp.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("message", "Sign Up Pressed")
                firebaseAnalytics.logEvent("register_pressed", bundle)
                //Todo implement function and make call to viewmodel
                /*
                    if (llamada a la funcion de registro, es exitosa) {
                        bundle.putString("message", "Sign Up Succeeded")
                        firebaseAnalytics.logEvent("sign_up_success", bundle)
                    } else {
                        bundle.pugString("message", "Sign Up Failed")
                        firebaseAnalytics.logEvent("sign_up_error", bundle)
                    }
                 */
            }
            etEmail.afterTextChanged {
                viewModel.validateForm(
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }
            etPassword.afterTextChanged {
                viewModel.validateForm(
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }
            etConfirmPassword.afterTextChanged {
                viewModel.validateForm(
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }
            btnSignUp.isEnabled = false
        }

    }
}