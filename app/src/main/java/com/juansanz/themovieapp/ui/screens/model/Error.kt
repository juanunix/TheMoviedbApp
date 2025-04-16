package com.juansanz.themovieapp.ui.screens.model

sealed interface Error {
    class Server(
        val code: Int,
    ) : Error

    object Connectivity : Error

    class Unknown(
        val message: String,
    ) : Error
}
