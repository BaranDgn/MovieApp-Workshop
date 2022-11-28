package com.example.moviews.login

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException


//instead of callbacks everywhere, we can simply call await()
//function to get the result
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun<T> Task<T>.await(): T? {
    return suspendCancellableCoroutine { cont->
        addOnCompleteListener{
            if(it.exception != null){
                cont.resumeWithException(it.exception!!)
            }else{
                cont.resume(it.result, null)
            }
        }
    }
}