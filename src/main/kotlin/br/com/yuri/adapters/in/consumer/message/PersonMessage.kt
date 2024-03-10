package br.com.yuri.adapters.`in`.consumer.message

data class PersonMessage(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)