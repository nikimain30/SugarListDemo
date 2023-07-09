package com.sugar.cosmetics.data.remote.api

import javax.inject.Inject

class SugarApiClient @Inject constructor(private val sugarApiService: SugarApiService) {

    suspend fun fetchData(url: String) = sugarApiService.fetchData(url= url)

}
