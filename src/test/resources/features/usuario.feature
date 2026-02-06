Feature: Cadastro de usuários

  Scenario: Criar um novo usuário com sucesso
    Dado que eu tenha os dados de um novo usuário
    Quando eu enviar a requisição para criar o usuário
    Então o usuário deve ser criado com sucesso

  Scenario: Não permitir criar usuário com email já existente
    Dado que eu já tenha um usuário cadastrado
    Quando eu tentar cadastrar outro usuário com o mesmo email
    Então o sistema não deve permitir o cadastro
