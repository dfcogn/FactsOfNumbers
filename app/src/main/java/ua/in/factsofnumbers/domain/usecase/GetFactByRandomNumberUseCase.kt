package ua.`in`.factsofnumbers.domain.usecase

import ua.`in`.factsofnumbers.domain.Resource
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.domain.repository.NumberRepository

class GetFactByRandomNumberUseCase(private val repository: NumberRepository) {
    suspend fun execute(): Resource<NumbersFact> {
        return repository.getFactByRandomNumber()
    }
}