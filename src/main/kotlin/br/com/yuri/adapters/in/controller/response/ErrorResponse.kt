package br.com.yuri.adapters.`in`.controller.response

data class ErrorResponse(
    val timestamp: String,
    val message: String,
    val details: String
)