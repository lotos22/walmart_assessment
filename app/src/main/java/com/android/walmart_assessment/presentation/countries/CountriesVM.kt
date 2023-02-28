package com.android.walmart_assessment.presentation.countries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.walmart_assessment.R
import com.android.walmart_assessment.data.Country
import com.android.walmart_assessment.domain.FetchCountriesUseCase
import com.android.walmart_assessment.utils.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountriesVM(
    private val fetchCountriesUseCase: FetchCountriesUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listCountries = MutableLiveData(listOf<Country>())
    val listCountries: LiveData<List<Country>> = _listCountries

    private val _errorMsg = MutableLiveData<Event<Int>>()
    val errorMsg: LiveData<Event<Int>> = _errorMsg

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = fetchCountriesUseCase()
            result.onSuccess {
                _listCountries.value = it
            }.onFailure {
                //map an errors and update data if it's any from local database
                when (it) {
                    is Exception -> _errorMsg.value = Event(R.string.generic_error)
                }
            }
        }.invokeOnCompletion {
            _isLoading.value = false
        }
    }

}