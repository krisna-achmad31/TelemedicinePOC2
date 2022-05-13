package com.telemedicine.indihealth.network.client

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.toResponseDataSource
import com.telemedicine.indihealth.network.service.splashScreen.SplashScreenResponse
import com.telemedicine.indihealth.network.service.splashScreen.SplashScreenService
import javax.inject.Inject

class SplashScreenClient @Inject constructor(
    private val splashScreenService: SplashScreenService
) {

    suspend fun getLogoApp(
        hashMap: HashMap<String, Any>,
        onResult: (splashScreenResponse: ApiResponse<SplashScreenResponse>) -> Unit
    ){
        splashScreenService
            .getAppLogo(hashMap)
            .toResponseDataSource()
            .request(onResult)
    }
}