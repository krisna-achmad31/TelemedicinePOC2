package com.telemedicine.indihealth.network.service.splashScreen

import retrofit2.http.*
import java.util.concurrent.locks.Condition
import com.skydoves.sandwich.DataSource

interface SplashScreenService {
    @GET("info?code=APP.LOGO")
    suspend fun getAppLogo(
        @QueryMap condition: HashMap<String,Any>
    ): DataSource<SplashScreenResponse>
}