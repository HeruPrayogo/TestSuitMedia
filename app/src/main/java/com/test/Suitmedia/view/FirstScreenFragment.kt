package com.test.Suitmedia.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.Suitmedia.R
import com.test.Suitmedia.ViewModel.UserViewModel
import com.test.Suitmedia.databinding.FragmentFirstScreenBinding
import com.test.Suitmedia.databinding.ItemUserBinding


class FirstScreenFragment : Fragment() {
    private lateinit var binding : FragmentFirstScreenBinding
    private lateinit var sharepref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharepref = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
        binding.buttonCheck.setOnClickListener {
            checkPalindrome()
        }
        binding.buttonNext.setOnClickListener {
            bawaNama()
        }

    }

    private fun checkPalindrome(){
        val palindrome = binding.textPalindrome.text
        if (palindrome!!.isNotEmpty()){
            val hapusSpasi = palindrome.replace("\\s".toRegex(), "").lowercase()
            val checkKata = hapusSpasi.reversed()
            if(hapusSpasi == checkKata){
                Toast.makeText(requireContext(), "Kata Tersebut Adalah Palindrome", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Kata Tersebut Bukan Palindrome", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Harap masukkan kata!!", Toast.LENGTH_SHORT).show()
        }

    }
    private fun bawaNama(){
        val getNama = binding.textName.text
        if(getNama!!.isNotEmpty()) {
            sharepref = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
            val sharePref = sharepref.edit()
            sharePref.putString("getNama", getNama.toString())
            sharePref.apply()
            findNavController().navigate(R.id.action_firstScreenFragment_to_secondScreenFragment)
        }else{
            Toast.makeText(requireContext(), "Harap masukkan Nama!!", Toast.LENGTH_SHORT).show()
        }
    }


}