package com.android.walmart_assessment.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.walmart_assessment.domain.FetchCountriesUseCase
import com.android.walmart_assessment.presentation.countries.CountriesVM

class CountriesVMFactory(
    private val fetchCountriesUseCase: FetchCountriesUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CountriesVM(fetchCountriesUseCase) as T
    }
}