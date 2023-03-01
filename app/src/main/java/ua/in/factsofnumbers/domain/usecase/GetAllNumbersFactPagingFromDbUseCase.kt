package ua.`in`.factsofnumbers.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.`in`.factsofnumbers.domain.Resource
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.domain.repository.NumberRepository

class GetAllNumbersFactPagingFromDbUseCase(private val repository: NumberRepository) {
    fun execute(): Flow<PagingData<NumbersFact>> {
        return repository.getAllNumbersFactPagingFromDb()
    }
}