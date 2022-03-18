package com.example.presentation.viewmodel

import androidx.compose.animation.core.snap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DomainLoveResponse
import com.example.domain.model.DomainScore
import com.example.domain.usecase.*
import com.example.domain.utiils.ErrorType
import com.example.domain.utiils.RemoteErrorEmitter
import com.example.domain.utiils.ScreenState
import com.example.presentation.widjet.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoveCalculatorUseCase: CheckLoveCalculatorUseCase,
    private val setStatisticsUseCase: SetStatisticsUseCase,
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val setScoreUseCase: SetScoreUseCase,
    private val getScoreUseCase: GetScoreUseCase
): ViewModel(), RemoteErrorEmitter{

    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent
    private val _apiCallEvent = SingleLiveEvent<ScreenState>()

    val getStatisticsEvent: LiveData<Int> get() = _getStatisticsEvent
    private val _getStatisticsEvent = SingleLiveEvent<Int>()

    val getScoreEvent: LiveData<Int> get() = _getScoreEvent
    private val _getScoreEvent = SingleLiveEvent<Int>()

    var apiCallResult = DomainLoveResponse("","",0,"")
    var apiErrorType = ErrorType.UNKNOWN
    var apiErrorMessage = "none"
    var manNameResult = "man"
    var womanNameResult = "woman"
    var scoreList = arrayListOf<DomainScore>()

    fun checkLoveCalculator(host: String, key: String, mName: String, wName: String) = viewModelScope.launch {
        checkLoveCalculatorUseCase.execute(this@MainViewModel, host, key, mName, wName).let { response ->
            if (response != null){
                apiCallResult = response
                _apiCallEvent.postValue(ScreenState.LOADING)
            }
            else _apiCallEvent.postValue(ScreenState.ERROR)
        }
    }

    fun setStatistics(plusValue: Int) = setStatisticsUseCase.execute(plusValue)

    fun getStatistics() = getStatisticsUseCase.execute()

    fun getStatisticsDisplay() = getStatisticsUseCase.execute()
        .addOnSuccessListener {
            _getStatisticsEvent.postValue(it.value.toString().toInt())
        }

    fun getScore() = getScoreUseCase.execute()
        .addOnSuccessListener { snapshot ->
            scoreList.clear()
            for (item in snapshot.documents){
                item.toObject(DomainScore::class.java).let {
                    scoreList.add(it!!)
                }
            }
            _getScoreEvent.call()
        }

    fun setScore(man : String, woman : String, percentage : Int, date: String){
        setScoreUseCase.execute(DomainScore(man, woman, percentage, date))
    }

    override fun onError(msg: String) {
        apiErrorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }
}