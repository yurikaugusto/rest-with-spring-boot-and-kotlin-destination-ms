package br.com.yuri.adapters.`in`.controller.request

data class PersonRequest(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)