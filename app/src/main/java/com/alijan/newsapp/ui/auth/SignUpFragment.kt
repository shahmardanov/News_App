package com.alijan.newsapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.newsapp.R
import com.alijan.newsapp.databinding.FragmentSignInBinding
import com.alijan.newsapp.databinding.FragmentSignUpBinding
import com.alijan.newsapp.util.gone
import com.alijan.newsapp.util.invisible
import com.alijan.newsapp.util.toastError
import com.alijan.newsapp.util.visible

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.buttonSignUpLogin.setOnClickListener {
            requestSignUp()
        }

        binding.textViewSignUpSignUp.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }
    }

    private fun requestSignUp(){
        if(checkInput()){
            val email = binding.inputSignUpEmail.text.toString().trim()
            val password = binding.inputSignUpPassword.text.toString().trim()

            viewModel.requestSignUp(email,password)
        } else {
            toastError(context,"Uğursuz cəhd","Xanaları tam doldurun!")
        }
    }

    private fun checkInput(): Boolean{
        val email = binding.inputSignUpEmail.text.toString().trim()
        val password = binding.inputSignUpPassword.text.toString().trim()
        val confirmPassword = binding.inputSignUpConfirmPassword.text.toString().trim()

        if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
            return password == confirmPassword
        }
        return false
    }

    private fun observeData(){
        viewModel.isLogin.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
            } else {
                toastError(context,"Uğursuz cəhd",viewModel.errorMessage.value.toString())
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it) {
                binding.progressBarSignUp.visible()
                binding.buttonSignUpLogin.invisible()
            } else {
                binding.progressBarSignUp.gone()
                binding.buttonSignUpLogin.visible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}