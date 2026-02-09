package br.com.serverest.steps; // define o pacote onde a classe está organizada (camada de steps do BDD)

import br.com.serverest.client.UsuarioClient; // importa o client responsável por fazer chamadas HTTP para o endpoint de usuários
import io.cucumber.java.pt.*; // importa as anotações do Cucumber em português (@Dado, @Quando, @Então)
import io.restassured.response.Response; // classe que representa a resposta retornada pela API

import static org.junit.jupiter.api.Assertions.*; // importa os métodos de asserção do JUnit (assertEquals, etc)

public class UsuarioSteps { // declaração da classe que contém os passos do Cucumber para usuário

    private UsuarioClient usuarioClient = new UsuarioClient(); // instancia o client que fará as requisições
    private Response response; // variável para armazenar a resposta da API entre os passos

    private String nome; // armazena o nome do usuario
    private String email; // armazena o email do usuario
    private String password; // armazena a senha do usuario
    private String administrador; // indica se o usuario e administrador

    // ---------- CENÁRIO FELIZ ----------

    @Dado("que eu tenha os dados de um novo usuário") // associa este metodo ao passo escrito no arquivo .feature
    public void que_eu_tenha_os_dados_de_um_novo_usuario() { // metodo responsável por preparar a massa de dados
        nome = "Usuario Teste"; // define um nome fixo para o usuário
        email = "teste_" + System.currentTimeMillis() + "@email.com"; // gera um email único usando timestamp
        password = "123456"; // define a senha
        administrador = "true"; // define que o usuário será administrador
    } // fim do passo Dado

    @Quando("eu enviar a requisição para criar o usuário") // passo que executa a ação principal
    public void eu_enviar_a_requisicao_para_criar_o_usuario() { // metodo que chama a API
        response = usuarioClient.criarUsuario( // chama o metodo do client para criar usuário e guarda a resposta
                nome, // envia o nome
                email, // envia o email
                password, // envia a senha
                administrador // envia se é administrador
        ); // fim da chamada
    } // fim do passo Quando

    @Então("o usuário deve ser criado com sucesso") // passo de validação do cenário feliz
    public void o_usuario_deve_ser_criado_com_sucesso() { // metodo de validação
        assertEquals(201, response.statusCode()); // verifica se o HTTP status retornado é 201 (Created)
        assertEquals( // compara a mensagem esperada com a retornada pela API
                "Cadastro realizado com sucesso", // mensagem esperada
                response.jsonPath().getString("message") // extrai o campo "message" do corpo da resposta
        ); // fim do assert da mensagem
    } // fim do passo Então

    // ---------- CENÁRIO DE ERRO ----------

    @Dado("que eu já tenha um usuário cadastrado") // pré-condição: já existe um usuário na base
    public void que_eu_ja_tenha_um_usuario_cadastrado() { // metodo que garante essa pré-condição
        nome = "Usuário Duplicado"; // define o nome
        email = "duplicado_" + System.currentTimeMillis() + "@email.com"; // gera email único
        password = "123456"; // define a senha
        administrador = "false"; // define que não é admin

        Response primeiraCriacao = usuarioClient.criarUsuario( // realiza a primeira criação do usuário
                nome, // envia o nome
                email, // envia o email
                password, // envia a senha
                administrador // envia perfil admin ou não
        ); // fim da chamada

        assertEquals(201, primeiraCriacao.statusCode()); // garante que a criação inicial funcionou
    } // fim do Dado

    @Quando("eu tentar cadastrar outro usuário com o mesmo email") // ação: repetir cadastro
    public void eu_tentar_cadastrar_outro_usuario_com_o_mesmo_email() { // metodo da tentativa duplicada
        response = usuarioClient.criarUsuario( // chama novamente o endpoint
                nome, // mesmo nome
                email, // mesmo email (causará erro)
                password, // mesma senha
                administrador // mesmo perfil
        ); // fim da chamada
    } // fim do Quando

    @Então("o sistema não deve permitir o cadastro") // validação do erro esperado
    public void o_sistema_nao_deve_permitir_o_cadastro() { // metodo de verificação
        assertEquals(400, response.statusCode()); // valida que retornou Bad Request
        assertEquals( // compara a mensagem de erro
                "Este email já está sendo usado", // mensagem esperada
                response.jsonPath().getString("message") // mensagem retornada pela API
        ); // fim do assert
    } // fim do Então
} // fim da classe
