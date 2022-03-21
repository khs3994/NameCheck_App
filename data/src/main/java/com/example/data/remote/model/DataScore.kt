package com.example.data.remote.model

data class DataScore(
    //남자
    val man : String,
    //여자
    val woman : String,
    //퍼센트
    val percentage : Int,
    //시간
    val date  : String
){
    constructor() : this("오류","오류",0,"오류")
}
