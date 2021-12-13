package br.com.devaria.projetos.gerenciador.de.tarefas.filters

import br.com.devaria.projetos.gerenciador.de.tarefas.authorization
import br.com.devaria.projetos.gerenciador.de.tarefas.bearer
import br.com.devaria.projetos.gerenciador.de.tarefas.impl.UsuarioDetalheImpl
import br.com.devaria.projetos.gerenciador.de.tarefas.models.Usuario
import br.com.devaria.projetos.gerenciador.de.tarefas.utils.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAutorizadorFilter(authenticationManager: AuthenticationManager, val jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManager){

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val athorization = request.getHeader("authorization")

        if(athorization != null && authorization.startsWith(bearer)) {
            val autorizado = getAuthentication(athorization)
            SecurityContextHolder.getContext().authentication = autorizado
        }

        chain.doFilter(request, response)

    }
    private fun getAuthentication(athorization: String): UsernamePasswordAuthenticationToken {
        val token = athorization.substring(7)
        if(jwtUtils.isTokenValido(token)) {
            val idString = jwtUtils.getUsuarioId(token)
            if(!idString.isNullOrBlank() && !idString.isNullOrEmpty()){
                val usuario = Usuario(idString.toLong(), "Usuario Teste", "admin@admin.com", "Admin1234@")
                val usuarioImpl = UsuarioDetalheImpl(usuario)
                return UsernamePasswordAuthenticationToken(usuario, null)
            }
        }
        throw UsernameNotFoundException("token informado nao esta valido, ou nao tem uma informacao de identificacao")
    }
}