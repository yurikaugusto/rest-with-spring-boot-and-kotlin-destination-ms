package br.com.yuri.adapters.`in`.controller.response

import java.util.*

data class ErrorResponse(
    val timestamp: String,
    val message: String,
    val details: String
)