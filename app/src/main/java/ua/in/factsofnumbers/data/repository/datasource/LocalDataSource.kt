package ua.`in`.factsofnumbers.data.repository.datasource

import androidx.lifecycle.LiveData
import ua.`in`.factsofnumbers.domain.model.NumbersFact

interface LocalDataSource {
    suspend fun saveFactToDb(numbersFact: NumbersFact)
    fun getAllSavedFacts(): LiveData<List<NumbersFact>>
    suspend fun getAllSavedFactsPaging(limit: Int, offset: Int): List<NumbersFact>
}