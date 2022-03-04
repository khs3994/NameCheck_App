package com.example.domain.repository

import com.example.domain.model.DomainLoveResponse
import com.example.domain.utiils.RemoteErrorEmitter

interface MainRepository {
    suspend fun checkLoveCalculator(
        remoteErrorEmitter: RemoteErrorEmitter,
        host : String,
        key : String,
        //fName : 남자이름
        mName : String,
        //sName : 여자이름
        wName : String
    ) : DomainLoveResponse?
}