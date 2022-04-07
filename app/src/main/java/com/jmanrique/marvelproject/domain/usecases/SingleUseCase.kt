package com.jmanrique.marvelproject.domain.usecases

import io.reactivex.rxjava3.core.Single

interface SingleUseCase<Params, Any> {

    /**
     *  Single always emits only one value or throws an error. Single always makes sure there is an emission.
     *  A use case of Single would be making a network call to get a response as the response will be fetched at once.
     */

    fun execute(params: Params): Single<Any>

}