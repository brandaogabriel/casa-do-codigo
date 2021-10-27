package com.brandao.casadocodigo.novacategoria

import com.brandao.casadocodigo.shared.validacao.CampoUnico
import javax.validation.constraints.NotBlank

data class NovaCategoriaRequest(
    @field:NotBlank
    @field:CampoUnico(fieldName = "nome", domainClass = Categoria::class)
    val nome: String
) {

    fun paraCategoria(): Categoria {
        return Categoria(nome)
    }
}
