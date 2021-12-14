package br.com.devaria.projetos.gerenciador.de.tarefas.controllers

import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.ErroDto
import br.com.devaria.projetos.gerenciador.de.tarefas.models.Usuario
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/usuario")
class UsuarioController {

    @PostMapping
    fun criarUsuario(@RequestBody usuario: Usuario): ResponseEntity<Any>{
        try{
            val erros = mutableListOf<String>()


            if(usuario == null || usuario.nome.isNullOrEmpty() || usuario.nome.isNullOrBlank()
                    || usuario.nome.length<2){
                erros.add("Nome de usuario nao valido")
            }

            if(usuario.email.isNullOrEmpty() || usuario.email.isNullOrBlank() || usuario.email.length<5) {
                erros.add("Email do usuario invalido")
            }

            if(usuario.senha.isNullOrEmpty() || usuario.senha.isNullOrBlank() || usuario.senha.length<4) {
                erros.add("Senha do usuario invalido ")
            }


            return ResponseEntity(usuario, HttpStatus.OK)
        }catch(excecao : Exception){
            return ResponseEntity(
            ErroDto(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Nao foi possivel cadastrar o usuario, tente novemente"), HttpStatus.INTERNAL_SERVER_ERROR)

        }
    }



}