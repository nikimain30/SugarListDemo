package com.sugar.cosmetics.data.repo

import com.sugar.cosmetics.data.remote.api.SugarApiClient
import javax.inject.Inject

class HomeRepository @Inject constructor(private val sugarApiClient: SugarApiClient) {

    suspend fun fetchData(url: String) = sugarApiClient.fetchData(url =url)
}