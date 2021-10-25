package com.brandao.casadocodigo.novoautor

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/autores")
class AutorController(
    val autorRepository: AutorRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun cadastrarAutor(@RequestBody @Valid request: NovoAutorRequest, builder: UriComponentsBuilder): ResponseEntity<Unit> {
        logger.info("method=cadastrarAutor, msg=cadastrando novo autor com email: {}", request.email)
        val autor = request.paraAutor()
        autorRepository.save(autor)

        val uri = builder.path("/api/v1/autores/{id}").buildAndExpand(autor.id).toUri()

        logger.info("method=cadastrarAutor, msg=autor de email: {}, cadastrado com sucesso", request.email)
        return ResponseEntity.created(uri).build()
    }
}