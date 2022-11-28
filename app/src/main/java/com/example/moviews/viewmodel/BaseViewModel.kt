package com.example.moviews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application : Application) : AndroidViewModel(application), CoroutineScope {

    //bunu yapmadan coroutini kullanamayız.
    //coroutinini ne yapacagını soyleriz

    //Arka planda yapılacak is olusturur.
    private val job = Job()

    //job + dispacther.  önce işini yap sonra main thread e don anlamına gelir.
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    //eğer ki birşey destroy olursa işi iptal etmek için kullanılır
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}