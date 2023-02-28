package ua.`in`.factsofnumbers.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ua.`in`.factsofnumbers.domain.model.NumbersFact

@Dao
interface NumbersFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFact(fact: NumbersFact)

    @Query("SELECT * FROM facts ORDER BY id DESC")
    fun getAllSavedFacts(): LiveData<List<NumbersFact>>
}