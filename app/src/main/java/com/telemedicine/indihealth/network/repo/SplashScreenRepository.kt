package com.telemedicine.indihealth.network.repo

import com.skydoves.sandwich.*
import com.telemedicine.indihealth.model.ConsultationDoctor
import com.telemedicine.indihealth.model.SplashScreen
import com.telemedicine.indihealth.network.client.SplashScreenClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val splashScreenClient: SplashScreenClient
) {
    suspend fun getLogoApp(
        hashMap: HashMap<String, Any>,
        onSuccess: (SplashScreen?) -> Unit,
        onError: (String?) -> Unit
    ) = withContext(Dispatchers.IO){
        splashScreenClient.getLogoApp(hashMap) {
            Timber.d("response .... $it")
            it.onSuccess {
                if (data != null){
                    if (data?.status!!){
                        onSuccess(data?.data)
                    } else {
                        Timber.d("is error = ${data?.msg}")
                        onError(data?.msg)
                    }
                }
            }
                .onError {
                    Timber.d("onError")
                    onError(message())
                }
                .onException {
                    Timber.d("onException")
                    onError(message())
                }
                .onFailure {
                    Timber.d("onFailure")
                    onError("error")
                }
        }
    }


}