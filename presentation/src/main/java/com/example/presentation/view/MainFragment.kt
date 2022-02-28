package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun init() {
        binding.fragment = this //메인 프라그먼트 data에 만들었던 fragment가 이곳임을 알려준다
    }

    fun startBtnClick(view: View){
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }
}