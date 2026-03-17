# language: pt
Funcionalidade: Cadastro de novo feedback de um cidadao
  Como usuário da API
  Quero cadastrar um novo feedback
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido do feedback
    Dado que eu tenha os seguintes dados do feedback
      | campo          | valor        |
      | autor          | Joao         |
      | tipo           | construtivo  |
      | descricao      | interessante |
      | data           | 2025-10-22   |
      | status         | ativa        |
    Quando eu enviar a requisição para o endpoint "/api/feedback-cidadao" de cadastro de feedback
    Então o status code da resposta deve ser 201

  Cenário: Cadastro de feedback sem sucesso ao passar o campo status inválido
    Dado que eu tenha os seguintes dados do feedback
      | campo          | valor        |
      | autor          | Joao         |
      | tipo           | construtivo  |
      | descricao      | interessante |
      | data           | 2025-10-22   |
      | status         | teste        |
    Quando eu enviar a requisição para o endpoint "/api/feedback-cidadao" de cadastro de feedback
    Então o status code da resposta deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "Dados fornecidos estão incorretos!"