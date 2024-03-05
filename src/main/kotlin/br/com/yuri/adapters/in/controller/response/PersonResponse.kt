package br.com.yuri.adapters.`in`.controller.response

data class PersonResponse(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)