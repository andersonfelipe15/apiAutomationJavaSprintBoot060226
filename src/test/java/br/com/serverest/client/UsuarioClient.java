package br.com.serverest.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsuarioClient {

    public Response criarUsuario(String nome, String email, String password, String administrador) {

        return given()
                .contentType("application/json")
                .body("""
                        {
                          "nome": "%s",
                          "email": "%s",
                          "password": "%s",
                          "administrador": "%s"
                        }
                        """.formatted(nome, email, password, administrador))
                .when()
                .post("/usuarios");
    }
}
