package com.example.domain.utiils

interface RemoteErrorEmitter {
    fun onError(msg:String)
    fun onError(errorType: ErrorType)
}