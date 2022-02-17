package com.example.jettrivia.data

data class DataOrException<T, Boolean, E:Exception> constructor(
    var data: T? = null,
    var loadingState: Boolean? = null,
    var e: E? = null
)