package br.com.yuri.adapters.`in`.consumer.message

import br.com.yuri.application.core.domain.PersonDomain

data class PersonMessage(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    var operationType: String = ""
)

fun PersonMessage.toPersonDomain(): PersonDomain = PersonDomain(
    this.id,
    this.firstName,
    this.lastName,
    this.address,
    this.gender
)