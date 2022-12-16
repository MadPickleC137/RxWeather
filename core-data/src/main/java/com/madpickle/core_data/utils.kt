package com.madpickle.core_data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm

/**
 * Created by David Madilyan on 04.12.2022.
 */

inline fun <T> Realm.executeSingle(crossinline transactionResult: () -> T): Single<T> {
    return Single.create { emitter ->
        executeTransactionAsync(Realm.Transaction {
            val result = transactionResult.invoke()
            if (result != null) {
                emitter.onSuccess(result)
            }else {
                emitter.onError(Throwable("Realm result is null object"))
            }
        }, Realm.Transaction.OnError {
            emitter.onError(it)
        })
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Realm.executeCompletable(transaction:() -> Unit): Completable {
    return Completable.create { completable ->
        this.executeTransactionAsync(
            { transaction.invoke() },
            { completable.onComplete() },
            { completable.onError(it) }
        )
    }
}