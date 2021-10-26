package com.brandao.casadocodigo.novoautor

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AutorRepository : JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>
}