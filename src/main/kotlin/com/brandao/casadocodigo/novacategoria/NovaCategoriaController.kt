package com.brandao.casadocodigo.novacategoria

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/categorias")
class NovaCategoriaController(
    val categoriaRepository: CategoriaRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun novaCategoria(@RequestBody @Valid request: NovaCategoriaRequest, builder: UriComponentsBuilder): ResponseEntity<Unit> {
        logger.info("method=novaCategoria, msg=cadastrando nova categoria: {}", request.nome)
        val categoria = request.paraCategoria()
        categoriaRepository.save(categoria)

        val uri = builder.path("/api/v1/categorias/{id}").buildAndExpand(categoria.id).toUri()
        logger.info("method=novaCategoria, msg=categoria: {} com id: {} cadastrada com sucesso", categoria.nome, categoria.id)
        return ResponseEntity.created(uri).build()
    }
}