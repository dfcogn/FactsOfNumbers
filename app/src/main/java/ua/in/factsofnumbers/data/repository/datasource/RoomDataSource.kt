package ua.`in`.factsofnumbers.data.repository.datasource

import androidx.lifecycle.LiveData
import ua.`in`.factsofnumbers.data.db.NumbersFactDao
import ua.`in`.factsofnumbers.domain.model.NumbersFact

class RoomDataSource(private val numbersFactDao: NumbersFactDao): LocalDataSource {
    override suspend fun saveFactToDb(numbersFact: NumbersFact) {
        return numbersFactDao.saveFact(numbersFact)
    }

    override fun getAllSavedFacts(): LiveData<List<NumbersFact>> {
        return numbersFactDao.getAllSavedFacts()
    }
}