package com.example.shemajamebelin5

sealed class ResultBuilder<T> {
    data class Success<T>(val list: T) : ResultBuilder<T>()
    data class Error<T>(val errorMSg: String) : ResultBuilder<T>()
}