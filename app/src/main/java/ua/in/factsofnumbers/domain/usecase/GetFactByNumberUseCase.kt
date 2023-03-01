package ua.`in`.factsofnumbers.domain.usecase

import ua.`in`.factsofnumbers.domain.Resource
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.domain.repository.NumberRepository

class GetFactByNumberUseCase(private val repository: NumberRepository) {
    suspend fun execute(number: Long): Resource<NumbersFact>{
        return repository.getFactByNumber(number)
    }
}