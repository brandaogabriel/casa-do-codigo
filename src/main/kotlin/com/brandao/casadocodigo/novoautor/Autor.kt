package com.brandao.casadocodigo.novoautor

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Autor(
    val nome: String,

    @Column(unique = true)
    val email: String,
    val descricao: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val createdAt = LocalDateTime.now()
}