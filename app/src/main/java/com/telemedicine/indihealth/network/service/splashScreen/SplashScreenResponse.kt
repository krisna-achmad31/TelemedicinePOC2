package com.telemedicine.indihealth.network.service.splashScreen

import com.telemedicine.indihealth.model.SplashScreen
import com.telemedicine.indihealth.model.User

data class SplashScreenResponse (
    val status:Boolean?=false,
    val msg: String?="",
    val data:SplashScreen?
        )