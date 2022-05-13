package com.telemedicine.indihealth.ui.fragment.splashscreen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.telemedicine.indihealth.base.BaseViewModel
import com.telemedicine.indihealth.helper.AppVar
import com.telemedicine.indihealth.helper.Event
import com.telemedicine.indihealth.helper.SingleLiveData
import com.telemedicine.indihealth.model.SplashScreen
import com.telemedicine.indihealth.network.repo.SplashScreenRepository
//import com.telemedicine.indihealth.persistence.AppPreference
import com.telemedicine.indihealth.persistence.SharedPreferenceApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.HashMap

class SplashScreenViewModel @ViewModelInject constructor(
    private val sharedPreference: SharedPreferenceApp,
//    private val appPreference: AppPreference,
    private val splashScreenRepository: SplashScreenRepository
) : BaseViewModel() {

    var condition: HashMap<String, Any> = hashMapOf()

    val logoSplashScreen: MutableLiveData<SplashScreen> by lazy {
        MutableLiveData<SplashScreen>()
    }

    fun logo() : String = "https://poc2.telemedical.id/assets/logo/logo-20220208-34501574c28f1b75a7c87760ce3ec8b48eed679a.png"



//    private var logoSplashScreenMaster : SplashScreen? = mutableListOf()

    fun setLogoSplashScreenAsync(it: SplashScreen?){
        logoSplashScreen.postValue(it)
    }

    fun getLogoApp(){
        setLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            splashScreenRepository.getLogoApp(
                condition,
                onSuccess = {
                    setLoading(false)
                    logoSplashScreen.postValue(it)
                    setLogoSplashScreenAsync(it)
                    Timber.d("the logo url is = ${it?.content}")
                    if (it != null) {
                        it.content?.let { it1 ->
                            sharedPreference.setString(AppVar.APP_LOGO, it1)
                            Timber.d("the logo logo url is = ${sharedPreference.getString(AppVar.APP_LOGO)}")
                        }
                    }
//                    it?.let {
//                        appPreference.saveString(AppPreference.URL_LOGO, it.content)
//                    }
                },
                onError = {
                    setLoading(false)
                    Timber.d("error = $it")
                    setLogoSplashScreenAsync(null)
                }
            )
        }
    }

    val isLoggedIn: SingleLiveData<Event<String>> by lazy {
        SingleLiveData<Event<String>>().apply {
            Timber.d("isLoggedIn = ${sharedPreference.getLoggedInStatus()}")
            if (sharedPreference.getLoggedInStatus()) {
                Timber.d("isLoggedIn = success${sharedPreference.getUserValue()?.role}")
                value = Event("success${sharedPreference.getUserValue()?.role}")
            }
        }
    }

    fun getLogInStatus(): String {
        sharedPreference.clearCallNotif()
        return (
                if (sharedPreference.getLoggedInStatus()) {
                    "success${sharedPreference.getUserValue()?.role}"
                } else {
                    "NO_DATA"
                }
                ).toString()
    }

    fun getBoardingStatus():Boolean{
        return sharedPreference.getBoardingStatus()
    }

    fun getLogOutStatus(): Boolean{
        return sharedPreference.getLogOutStatus()
    }
}