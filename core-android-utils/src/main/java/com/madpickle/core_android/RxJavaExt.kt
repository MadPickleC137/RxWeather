package com.madpickle.core_android

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by David Madilyan on 25.08.2022.
 */

fun <T : Any> Observable<T>.setObserverIO(observer: Observer<T>){
    this.subscribeOn(Schedulers.io()).subscribe(observer)
}
fun Completable.onCompleted(){
    this.doOnSubscribe {  }.doOnComplete {  }
}