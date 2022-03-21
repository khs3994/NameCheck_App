package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.adapter.ScoreRecyclerViewAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.viewmodel.MainViewModel
import com.example.presentation.widjet.extention.showVertical
import com.pss.library.CountNumberEvent.Companion.countNumber

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun init() {
        binding.fragment = this //메인 프라그먼트 data에 만들었던 fragment가 이곳임을 알려준다
        observeViewModel()
        mainViewModel.getStatisticsDisplay()
        mainViewModel.getScore()
    }

    fun startBtnClick(view: View){
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }

    private fun observeViewModel(){
        mainViewModel.getStatisticsEvent.observe(this){
            countNumber(0,it,binding.startistics,2000)
        }

        mainViewModel.getScoreEvent.observe(this){
            initRecyclerView()
        }
    }

    private fun initRecyclerView(){
        binding.scoreRecyclerView.adapter = ScoreRecyclerViewAdapter(mainViewModel)
        binding.scoreRecyclerView.showVertical(requireContext())
    }
}