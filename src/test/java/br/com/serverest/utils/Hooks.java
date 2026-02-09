package br.com.serverest.utils; // define o pacote de utilidades compartilhadas do projeto

import io.cucumber.java.Before; // import da anotacao que executa codigo antes de cada cenario
import io.restassured.RestAssured; // classe principal do RestAssured para configuracoes globais

public class Hooks { // classe que centraliza preparacoes e finalizacoes dos testes

    @Before // indica que o metodo sera executado antes de cada cenario do Cucumber
    public void setup() { // metodo responsavel pela configuracao inicial dos testes
        RestAssured.baseURI = "https://serverest.dev"; // define a URL base da API, evitando repetir em todas as chamadas
        RestAssured.basePath = ""; // define um caminho base para os endpoints (aqui esta vazio)
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // habilita log automatico da request e response quando um teste falha
    } // fim do metodo de setup
} // fim da classe
