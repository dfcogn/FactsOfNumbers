package ua.`in`.factsofnumbers.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberService {
    @GET("{number}")
    suspend fun getFactByNumber(@Path("number") number: Long): Response<String>

    @GET("random/math")
    suspend fun getFactByRandomNumber(): Response<String>
}