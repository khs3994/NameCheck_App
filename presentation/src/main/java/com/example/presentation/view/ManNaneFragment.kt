package com.example.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.utiils.ErrorType
import com.example.domain.utiils.ScreenState
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentManNaneBinding
import com.example.presentation.viewmodel.MainViewModel


class ManNaneFragment : BaseFragment<FragmentManNaneBinding>(R.layout.fragment_man_nane) {

    private val mainViewModel by activityViewModels<MainViewModel>()
    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun nextBtnClick(view: View) {
        binding.loadingBar.visibility = View.VISIBLE
        mainViewModel.checkLoveCalculator(
            "love-calculator.p.rapidapi.com",
            "c83af352eemsh05551c5bb5ecf9cp1ea0c8jsn750b207adb9a",
            binding.nameEditTxt.text.toString(),
            mainViewModel.womanNameResult
        )
        this.findNavController().navigate(R.id.action_manNaneFragment_to_resultFragment)
    }

    private fun observeViewModel() {
        mainViewModel.apiCallEvent.observe(this) {
            binding.loadingBar.visibility = View.INVISIBLE
            when (it) {
                ScreenState.LOADING -> this.findNavController()
                    .navigate(R.id.action_manNaneFragment_to_resultFragment)
                ScreenState.ERROR -> toastErrorMsg()
                else -> shortShowToast("알수없는 오류가 발생했습니다")
            }
        }
    }

    private fun toastErrorMsg() {
        when (mainViewModel.apiErrorType) {
            ErrorType.NETWORK -> longShowToast("네트워크 오류가 발생했습니다")
            ErrorType.SESSION_EXPIRED -> longShowToast("세션이 만료되었습니다")
            ErrorType.TIMEOUT -> longShowToast("호출 시간이 초과되었습니다")
            ErrorType.UNKNOWN -> longShowToast("예기치 못한 오류가 발생했습니다")
        }
    }
}