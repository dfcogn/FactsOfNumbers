package ua.`in`.factsofnumbers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.domain.repository.NumberRepository
import ua.`in`.factsofnumbers.domain.Resource

class HomeViewModel(private val repository: NumberRepository): ViewModel() {
    private var _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> get() = _isProgress

    private var _responseNewFactOk = MutableLiveData<Event<NumbersFact>>()
    val responseNewFactOk: LiveData<Event<NumbersFact>> get() = _responseNewFactOk

    private var _responseNewFactError = MutableLiveData<Event<String>>()
    val responseNewFactError: LiveData<Event<String>> get() = _responseNewFactError

    private var _isEmptyNumber = MutableLiveData<Event<Boolean>>()
    val isEmptyNumber: LiveData<Event<Boolean>> get() = _isEmptyNumber

    fun downloadFactByNumber(number: String){
        if (!isValidNumber(number)){
            _isEmptyNumber.value = Event(true)
            return
        }
        _isProgress.value = true
        viewModelScope.launch {
            val response = repository.getFactByNumber(number.toLong())
            _isProgress.value = false

            when(response){
                is Resource.Success -> {
                    _responseNewFactOk.value = Event(response.data!!)
                }
                is Resource.Error -> {
                    if(!response.message.isNullOrBlank()){
                        _responseNewFactError.value = Event(response.message)
                    }
                }
            }
        }
    }

    fun downloadFactByRandomNumber(){
        _isProgress.value = true
        viewModelScope.launch {
            val response = repository.getFactByRandomNumber()
            _isProgress.value = false

            when(response){
                is Resource.Success -> {
                    _responseNewFactOk.value = Event(response.data!!)
                }
                is Resource.Error -> {
                    if(!response.message.isNullOrBlank()){
                        _responseNewFactError.value = Event(response.message)
                    }
                }
            }
        }
    }

    fun getAllNumbersFactFromDb(): LiveData<List<NumbersFact>>{
        return repository.getAllNumbersFactFromDb()
    }

    private fun isValidNumber(numberStr: String): Boolean{
        return if(numberStr.isBlank()) return false else true
    }
}