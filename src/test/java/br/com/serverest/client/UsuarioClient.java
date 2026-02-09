package br.com.serverest.client; // define o pacote onde ficam as classes responsaveis por chamar a API

import io.restassured.response.Response; // classe que representa o retorno da requisicao HTTP

import static io.restassured.RestAssured.given; // importa o metodo given() do RestAssured para iniciar a construcao da request

public class UsuarioClient { // classe que encapsula as chamadas relacionadas ao recurso de usuarios

    public Response criarUsuario(String nome, String email, String password, String administrador) { // metodo publico que realiza a criacao de um usuario

        return given() // inicia a construção da requisição HTTP
                .contentType("application/json") // informa que o corpo da requisicao será enviado em JSON
                // início de um JSON em formato text block (Java 15+)
                .body(""" 
                        {
                          "nome": "%s", 
                          "email": "%s", 
                          "password": "%s", 
                          "administrador": "%s"
                        }
                        """.formatted(nome, email, password, administrador)) // substitui os %s pelos valores recebidos no metodo
                .when() // indica o momento da acao da requisicao
                .post("/usuarios"); // executa um POST no endpoint /usuarios e retorna a resposta
    } // fim do metodo
} // fim da classe
