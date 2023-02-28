package ua.`in`.factsofnumbers.data.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.data.repository.datasource.LocalDataSource
import ua.`in`.factsofnumbers.data.repository.datasource.RemoteDataSource
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import ua.`in`.factsofnumbers.domain.Resource

class NumberRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val resources: Resources
): NumberRepository, BaseSafeRepo() {
    override suspend fun getFactByNumber(number: Long): Resource<NumbersFact> {
        val response = getFactByNumberSafe(resources, number){
            remoteDataSource.getFactByNumber(number)
        }

        if(response is Resource.Success && response.data != null){
            saveNumbersFactToDb(response.data)
        }

        return response
    }

    override suspend fun getFactByRandomNumber(): Resource<NumbersFact> {
        val response = getFactByRandomNumberSafe(resources){
            remoteDataSource.getFactByRandomNumber()
        }

        if(response is Resource.Success && response.data != null){
            saveNumbersFactToDb(response.data)
        }

        return response
    }

    override fun getAllNumbersFactFromDb(): LiveData<List<NumbersFact>> {
        return localDataSource.getAllSavedFacts()
    }

    override suspend fun saveNumbersFactToDb(numbersFact: NumbersFact) {
        localDataSource.saveFactToDb(numbersFact)
    }
}