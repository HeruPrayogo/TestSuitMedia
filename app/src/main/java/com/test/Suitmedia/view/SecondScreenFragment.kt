package com.test.Suitmedia.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.Suitmedia.R
import com.test.Suitmedia.ViewModel.UserViewModel
import com.test.Suitmedia.databinding.FragmentSecondScreenBinding


class SecondScreenFragment : Fragment() {
   private lateinit var binding: FragmentSecondScreenBinding
   private lateinit var sharedPref: SharedPreferences
   private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val getUser = arguments?.getInt("ID")
        if (getUser != null) {
            userViewModel.getSelecetedUser(getUser)
        }
        userViewModel.liveSelectedUser.observe(viewLifecycleOwner){
            binding.getSelected.text = it.firstName + " " + it.lastName
        }
        sharedPref = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
        getDataNama()
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_secondScreenFragment_to_firstScreenFragment)
        }
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_secondScreenFragment_to_thirdScreenFragment)
        }
    }
    private fun getDataNama(){
        sharedPref = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
        val setNama = sharedPref.getString("getNama", "")
        binding.nama.text = setNama
    }

}