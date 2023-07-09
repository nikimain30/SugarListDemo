package com.sugar.cosmetics.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.sugar.cosmetics.data.model.FetchDetail
import com.sugar.cosmetics.data.remote.api.Resource
import com.sugar.cosmetics.data.remote.api.apiCall
import com.sugar.cosmetics.data.remote.model.FetchDetailDTO
import com.sugar.cosmetics.data.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.http.Url
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @ApplicationContext private val context: Context
): BaseViewModel() {

    val TAG = HomeRepository::class.java.simpleName
    var homeLiveData = MutableLiveData<Resource<FetchDetail>>()
    private var fetchData = FetchDetail()

    fun fetchData(url: String) {
        launchOnViewModelScope {
            homeLiveData.value = Resource.loading()
            apiCall<FetchDetailDTO?>(context) {
                onEnqueue = { homeRepository.fetchData(url) }
                onSuccess = { data ->
                    data?.let {
                        fetchData.disclaimer = data.disclaimer
                        fetchData.license = data.license
                        fetchData.timestamp = data.timestamp
                        fetchData.base = data.base
                        fetchData.rates = data.rates
                        homeLiveData.value = Resource.success(fetchData)
                    }

                }
                onError = {
                    homeLiveData.value = Resource.error(it)
                }
            }

        }
    }

}