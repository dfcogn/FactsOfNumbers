package ua.`in`.factsofnumbers.data.repository.datasource

import retrofit2.Response
import ua.`in`.factsofnumbers.data.api.NumberService

class RetrofitDataSource(private val numberService: NumberService): RemoteDataSource {
    override suspend fun getFactByNumber(number: Long): Response<String> {
        return numberService.getFactByNumber(number)
    }

    override suspend fun getFactByRandomNumber(): Response<String> {
        return numberService.getFactByRandomNumber()
    }
}