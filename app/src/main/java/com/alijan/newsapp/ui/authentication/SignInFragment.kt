package com.alijan.newsapp.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.newsapp.databinding.FragmentSignInBinding
import com.alijan.newsapp.util.gone
import com.alijan.newsapp.util.invisible
import com.alijan.newsapp.util.toastError
import com.alijan.newsapp.util.visible

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.buttonSignInLogin.setOnClickListener {
            requestSignUp()
        }

        binding.textViewSignInSignUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
        }
    }

    private fun requestSignUp(){
        if(checkInput()){
            val email = binding.inputSignInEmail.text.toString().trim()
            val password = binding.inputSignInPassword.text.toString().trim()

            viewModel.requestSignIn(email,password)
        } else {
            toastError(context,"Attempt failed, please check again","Fill in the boxes completely")
        }
    }

    private fun checkInput(): Boolean{
        val email = binding.inputSignInEmail.text.toString().trim()
        val password = binding.inputSignInPassword.text.toString().trim()

        if(email.isNotEmpty() && password.isNotEmpty()){
            return true
        }
        return false
    }

    private fun observeData(){
        viewModel.isLogin.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
            } else {
                toastError(context,"Attempt failed, please check again","Enter your e-mail or password correctly!")
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it) {
                binding.progressBarSignIn.visible()
                binding.buttonSignInLogin.invisible()
            } else {
                binding.progressBarSignIn.gone()
                binding.buttonSignInLogin.visible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}