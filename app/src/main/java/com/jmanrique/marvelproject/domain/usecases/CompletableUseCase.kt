package com.jmanrique.marvelproject.domain.usecases

import io.reactivex.rxjava3.core.Completable

interface CompletableUseCase<Params> {

    /**
     *  Completable observable won’t emit any data instead it notifies the status of the task either success or failure.
     *  This observable can be used when you want to perform some task and not expect any value.
     */
    fun execute(params: Params): Completable

}