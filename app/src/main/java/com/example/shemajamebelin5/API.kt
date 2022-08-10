package com.example.shemajamebelin5
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET(END_POINT)
    suspend fun getItems(): Response<List<List<Datas>>>

    companion object {
        const val END_POINT = "/v3/b0c6d294-e43e-4552-8a96-88897daf0ab5"
    }
}