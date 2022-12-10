package com.madpickle.core_data

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm

/**
 * Created by David Madilyan on 04.12.2022.
 */

inline fun <T> Realm.executeSingleAsync(crossinline transactionResult: () -> T): Single<T> {
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
    }
}

fun Realm.executeCompletable(transaction:(Realm) -> Unit): Completable {
    return Completable.create { completable ->
        this.executeTransactionAsync(
            transaction,
            { completable.onComplete() },
            { completable.onError(it) }
        )
    }
}

fun getSingleInstance(): Single<Boolean> = Single.just(true)
    .observeOn(Schedulers.newThread())