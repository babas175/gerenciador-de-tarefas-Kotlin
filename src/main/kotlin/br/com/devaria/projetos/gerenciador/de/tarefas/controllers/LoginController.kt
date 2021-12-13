package br.com.devaria.projetos.gerenciador.de.tarefas.controllers

import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.DtoLogin
import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.ErroDto
import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.LoginResposta
import br.com.devaria.projetos.gerenciador.de.tarefas.utils.JWTUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/login")



class LoginController {

    private val LOGIN_TESTE = "Admin@admin.com"
    private val SENHA_TESTE = "Admin1234@"

    @PostMapping
    fun efetuarLogin(@RequestBody Dto: DtoLogin): ResponseEntity<Any> {
        try {
            if(Dto==null || Dto.login.isNullOrBlank() || Dto.login.isNullOrEmpty()
                    || Dto.senha.isNullOrEmpty() || Dto.senha.isNullOrBlank()
            ||Dto.login != LOGIN_TESTE || Dto.senha != SENHA_TESTE){
                return ResponseEntity(ErroDto(HttpStatus.BAD_REQUEST.value(),
                        "Parametros de entrada invalidos"), HttpStatus.BAD_REQUEST)
            }
            val idUsuario=1
            val token = JWTUtils().gerarToken(idUsuario.toString())

            val usuarioTeste = LoginResposta("Usuario Teste ", LOGIN_TESTE)
            return ResponseEntity(usuarioTeste, HttpStatus.OK)

        } catch (e: Exception) {
            return ResponseEntity(ErroDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "erro nao foi possivel"), HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

}

