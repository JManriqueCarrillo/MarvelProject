package com.jmanrique.marvelproject.app.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    protected fun <T> subscribe(
        observable: Single<T>,
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit)? = null
    ): Disposable? {
        val o = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        val d = if (onError == null) o.subscribe(onSuccess) else o.subscribe(onSuccess, onError);
        disposables.add(d)
        return d
    }

    protected fun subscribe(
        observable: Completable,
        onComplete: (() -> Unit),
        onError: ((t: Throwable) -> Unit)? = null
    ): Disposable? {
        val o = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        val d = if (onError == null) o.subscribe(onComplete) else o.subscribe(onComplete, onError);
        disposables.add(d)
        return d
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}