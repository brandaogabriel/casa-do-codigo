package com.brandao.casadocodigo.novoautor

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class NovoAutorRequest(
    @NotBlank
    val nome: String,

    @NotBlank
    @Email
    val email: String,

    @NotBlank
    @Size(min = 1, max = 400)
    val descricao: String,
) {

    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }
}
