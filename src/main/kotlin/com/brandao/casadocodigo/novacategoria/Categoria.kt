package com.brandao.casadocodigo.novacategoria

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Categoria(
    @Column(unique = true)
    val nome: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val createdAt = LocalDateTime.now()
}