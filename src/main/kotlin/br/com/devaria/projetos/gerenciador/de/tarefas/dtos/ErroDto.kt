package br.com.devaria.projetos.gerenciador.de.tarefas.dtos

class ErroDto( val status:Int, val erro: String?= null, val erros: List<String>? = null)
