package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DomainLoveResponse
import com.example.domain.usecase.CheckLoveCalculatorUseCase
import com.example.domain.utiils.ErrorType
import com.example.domain.utiils.RemoteErrorEmitter
import com.example.domain.utiils.ScreenState
import com.example.presentation.widjet.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoveCalculatorUseCase: CheckLoveCalculatorUseCase
): ViewModel(), RemoteErrorEmitter{

    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent
    private val _apiCallEvent = SingleLiveEvent<ScreenState>()

    var apiCallResult = DomainLoveResponse("","",0,"")
    var apiErrorType = ErrorType.UNKNOWN
    var apiErrorMessage = "none"
    var manNameResult = "man"
    var womanNameResult = "woman"

    fun checkLoveCalculator(host: String, key: String, mName: String, wName: String) = viewModelScope.launch {
        checkLoveCalculatorUseCase.excute(this@MainViewModel, host, key, mName, wName).let { response ->
            if (response != null){
                apiCallResult = response
                _apiCallEvent.postValue(ScreenState.LOADING)
            }
            else _apiCallEvent.postValue(ScreenState.ERROR)
        }
    }

    override fun onError(msg: String) {
        apiErrorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }
}