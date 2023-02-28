package ua.`in`.factsofnumbers.domain

import ua.`in`.factsofnumbers.domain.model.NumbersFact

object FactMapper {
    fun getNumbersFactFromString(factString: String): NumbersFact {
        val number = factString.substringBefore(" ").toLong()
        return NumbersFact(number = number, description = factString)
    }
}