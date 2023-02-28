package ua.`in`.factsofnumbers.data.repository.datasource

import retrofit2.Response

interface RemoteDataSource {
    suspend fun getFactByNumber(number: Long): Response<String>
    suspend fun getFactByRandomNumber(): Response<String>
}