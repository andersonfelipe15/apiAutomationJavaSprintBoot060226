Feature: Cadastro de usuários // descreve a funcionalidade que está sendo validada neste arquivo

  Scenario: Criar um novo usuário com sucesso // cenário positivo (fluxo feliz)
    Dado que eu tenha os dados de um novo usuário // pré-condição: preparar as informações necessárias
    Quando eu enviar a requisição para criar o usuário // ação principal: chamada ao endpoint
    Então o usuário deve ser criado com sucesso // resultado esperado da operação

  Scenario: Não permitir criar usuário com email já existente // cenário negativo de regra de negócio
    Dado que eu já tenha um usuário cadastrado // pré-condição: garantir que o email já existe
    Quando eu tentar cadastrar outro usuário com o mesmo email // ação: tentativa de duplicidade
    Então o sistema não deve permitir o cadastro // resultado esperado: erro retornado pela API
