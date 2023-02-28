package com.android.walmart_assessment.domain

import com.android.walmart_assessment.data.Country

class FetchCountriesUseCase(
    private val repository: ICountriesRepository
) {
    suspend operator fun invoke(): Result<List<Country>> {
        return repository.getCountriesList()
    }
}