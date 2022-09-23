package com.madpickle.core_data.source

import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_data.room_db.dao.CurrentWeatherDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by David Madilyan on 28.08.2022.
 */

interface ICurrentWeatherDataSource {

}

class CurrentWeatherDataSource @Inject constructor(private val dao: CurrentWeatherDao): ICurrentWeatherDataSource {
//    fun subscribeCurrent(consumer: Consumer<CurrentModel>){
//        dao.getCurrent().observeOn(AndroidSchedulers.mainThread()).subscribe(consumer)
//    }
//
//    fun insertCurrent(currentModel: CurrentModel, completable: CompletableObserver){
//        dao.insertOrUpdate(currentModel).observeOn(Schedulers.io()).subscribe(completable)
//    }
}