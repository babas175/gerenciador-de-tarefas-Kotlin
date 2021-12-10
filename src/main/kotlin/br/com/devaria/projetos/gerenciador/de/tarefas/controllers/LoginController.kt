package br.com.devaria.projetos.gerenciador.de.tarefas.controllers

import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.DtoLogin
import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.ErroDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/login")

class LoginController {
    @PostMapping
    fun efetuarLogin(@RequestBody Dto: DtoLogin): ResponseEntity<Any> {
        try {
            throw RuntimeException("testando uma excecao")
        } catch (e: Exception) {
            return ResponseEntity(ErroDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "erro nao foi possivel"), HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

}