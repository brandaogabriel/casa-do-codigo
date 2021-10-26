package com.brandao.casadocodigo.novoautor

import com.brandao.casadocodigo.shared.handler.validacao.EmailUnico
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class NovoAutorRequest(
    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:Email
    @field:EmailUnico
    val email: String,

    @field:NotBlank
    @field:Size(min = 1, max = 400)
    val descricao: String,
) {

    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }
}
