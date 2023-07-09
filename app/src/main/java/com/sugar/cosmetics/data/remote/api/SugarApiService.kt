package com.sugar.cosmetics.data.remote.api

import com.sugar.cosmetics.data.remote.model.FetchDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface SugarApiService {

    @GET()
    suspend fun fetchData(
        @Header("Content-Type") contentType: String? ="application/json",
        @Url url: String
    ): Response<FetchDetailDTO?>
}