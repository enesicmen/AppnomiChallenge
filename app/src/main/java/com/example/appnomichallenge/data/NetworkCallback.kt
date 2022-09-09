package com.example.appnomichallenge.data

interface NetworkCallback <T>{
    fun onSuccess(data: T)
    fun onError(message: String)
}