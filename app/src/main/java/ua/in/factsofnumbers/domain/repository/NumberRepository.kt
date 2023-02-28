package ua.`in`.factsofnumbers.domain.repository

import androidx.lifecycle.LiveData
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.domain.Resource

interface NumberRepository {
    suspend fun getFactByNumber(number: Long): Resource<NumbersFact>
    suspend fun getFactByRandomNumber(): Resource<NumbersFact>
    suspend fun saveNumbersFactToDb(numbersFact: NumbersFact)
    fun getAllNumbersFactFromDb(): LiveData<List<NumbersFact>>
//    fun getNumbersFactFromDbById(id: Long): LiveData<NumbersFact>
}