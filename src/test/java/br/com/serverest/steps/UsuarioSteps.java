package br.com.serverest.steps;

import br.com.serverest.client.UsuarioClient;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioSteps {

    private UsuarioClient usuarioClient = new UsuarioClient();
    private Response response;

    private String nome;
    private String email;
    private String password;
    private String administrador;

    // ---------- CENÁRIO FELIZ ----------

    @Dado("que eu tenha os dados de um novo usuário")
    public void que_eu_tenha_os_dados_de_um_novo_usuario() {
        nome = "Usuário Teste";
        email = "teste_" + System.currentTimeMillis() + "@email.com";
        password = "123456";
        administrador = "true";
    }

    @Quando("eu enviar a requisição para criar o usuário")
    public void eu_enviar_a_requisicao_para_criar_o_usuario() {
        response = usuarioClient.criarUsuario(
                nome,
                email,
                password,
                administrador
        );
    }

    @Então("o usuário deve ser criado com sucesso")
    public void o_usuario_deve_ser_criado_com_sucesso() {
        assertEquals(201, response.statusCode());
        assertEquals(
                "Cadastro realizado com sucesso",
                response.jsonPath().getString("message")
        );
    }

    // ---------- CENÁRIO DE ERRO ----------

    @Dado("que eu já tenha um usuário cadastrado")
    public void que_eu_ja_tenha_um_usuario_cadastrado() {
        nome = "Usuário Duplicado";
        email = "duplicado_" + System.currentTimeMillis() + "@email.com";
        password = "123456";
        administrador = "false";

        Response primeiraCriacao = usuarioClient.criarUsuario(
                nome,
                email,
                password,
                administrador
        );

        assertEquals(201, primeiraCriacao.statusCode());
    }

    @Quando("eu tentar cadastrar outro usuário com o mesmo email")
    public void eu_tentar_cadastrar_outro_usuario_com_o_mesmo_email() {
        response = usuarioClient.criarUsuario(
                nome,
                email,
                password,
                administrador
        );
    }

    @Então("o sistema não deve permitir o cadastro")
    public void o_sistema_nao_deve_permitir_o_cadastro() {
        assertEquals(400, response.statusCode());
        assertEquals(
                "Este email já está sendo usado",
                response.jsonPath().getString("message")
        );
    }
}
