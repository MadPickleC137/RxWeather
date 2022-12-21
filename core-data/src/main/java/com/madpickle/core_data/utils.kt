package com.madpickle.core_data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm
import io.realm.exceptions.RealmException
import io.realm.exceptions.RealmMigrationNeededException
import timber.log.Timber
import java.lang.IllegalArgumentException

/**
 * Created by David Madilyan on 04.12.2022.
 */

internal inline fun <T> executeSingle(crossinline transactionResult: (Realm) -> T?): Single<T> {
    return Single.create { emitter ->
        Realm.getDefaultInstance().use { realm ->
            try{
                realm.executeTransaction {
                    val result = transactionResult.invoke(realm)
                    if (result != null) {
                        emitter.onSuccess(result)
                    } else {
                        emitter.onError(Throwable("Realm result is null object"))
                    }
                }
            }catch (e: IllegalArgumentException){
                Timber.e(e)
                emitter.onError(e)
            }catch (e: RealmMigrationNeededException){
                Timber.e(e)
                emitter.onError(e)
            }catch (e: RealmException){
                Timber.e(e)
                emitter.onError(e)
            }
        }
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

internal fun executeCompletable(transaction:(Realm) -> Unit): Completable {
    return Completable.create { emitter ->
        Realm.getDefaultInstance().use { realm ->
            try{
                realm.executeTransaction {
                    transaction.invoke(realm)
                }
            }catch (e: IllegalArgumentException){
                Timber.e(e)
                emitter.onError(e)
            }catch (e: RealmMigrationNeededException){
                Timber.e(e)
                emitter.onError(e)
            }catch (e: RealmException){
                Timber.e(e)
                emitter.onError(e)
            }finally {
                emitter.onComplete()
            }
        }
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}