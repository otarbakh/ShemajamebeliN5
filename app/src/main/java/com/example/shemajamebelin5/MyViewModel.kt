package com.example.shemajamebelin5

import androidx.lifecycle.ViewModel
import com.example.shemajamebelin5.RetrofitBuilder.getApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyViewModel : ViewModel() {
    fun getList(): Flow<ResultBuilder<List<List<Datas>>>> = flow {
        val answerFromServer = getApi().getItems()
        val response: ResultBuilder<List<List<Datas>>> = when {
            answerFromServer.isSuccessful -> {
                ResultBuilder.Success(list = answerFromServer.body()!!)
            }
            else -> {
                ResultBuilder.Error(errorMSg = answerFromServer.errorBody().toString())
            }
        }
        emit(response)
    }
}