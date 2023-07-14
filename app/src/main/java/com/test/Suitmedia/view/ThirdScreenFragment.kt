package com.test.Suitmedia.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.Suitmedia.R
import com.test.Suitmedia.ViewModel.UserViewModel
import com.test.Suitmedia.databinding.FragmentThirdScreenBinding
import com.test.Suitmedia.view.adapter.UserAdapter


class ThirdScreenFragment : Fragment() {
    lateinit var binding: FragmentThirdScreenBinding
    lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUser()
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_thirdScreenFragment_to_secondScreenFragment)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getUser(){
        val getVM = ViewModelProvider(this)[UserViewModel::class.java]
        userAdapter = UserAdapter(emptyList()){
            var bundle = Bundle().apply {
                putInt("ID", it.id)
            }
            findNavController().navigate(R.id.action_thirdScreenFragment_to_secondScreenFragment, bundle)
        }
        getVM.getUsers()
        binding.rvcon.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvcon.adapter = userAdapter
        getVM.liveDataUser.observe(viewLifecycleOwner){
            userAdapter.listUser = it
            userAdapter.notifyDataSetChanged()
        }
    }

}