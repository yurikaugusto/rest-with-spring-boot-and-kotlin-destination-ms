package br.com.yuri.application.core.domain

data class PersonDomain(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)