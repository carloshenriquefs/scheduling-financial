# :dollar: - Desafio DSMovie-RestAssured

- Desenvolver um sistema de agendamento de transferências financeiras;

## :pushpin: - Entregáveis:

- Aplicação + pequena documentação no README explicando suas decisões arquiteturais, versões de linguagem, ferramentas utilizadas e instruções para a subido do projeto.

## :white_check_mark: - Avaliação:

- Desenvolver tanto a API quanto o front-end (Spring boot e VueJs no front, caso não tenha conhecimento de VueJs, pode ser feito o front com angular). **Utilizar a versão 11 do Java**;

- O objetivo dessa tarefa é avaliar como você vai desenvolver o código em termos de estilo, eficiência, qualidade e prazo de entrega;

## :book: - Tarefa:

- Desenvolver um sistema de agendamento de transferências financeiras.
- O usuário deve poder agendar uma transferência financeira com as seguintes informações:

## :scroll: - Regras:

1 - Conta de origem (padrão XXXXXXXXXX), conta de destino (padrão XXXXXXXXXX), valor da transferência, taxa (a ser calculada conforme tabela abaixo), data da transferência (data em que será realizada a transferência) e data de agendamento (hoje);

2 - O cálculo da taxa sobre o **valor a ser transferido** depende da data de transferência como segue:

| Dias Transferência |    R$    |   Taxa   |
|:------------------:|:--------:|:--------:|
|    De   |    Até   |          |          |
|     0   |     0    |   3,00   |   2,5%   |
|     1   |    10    |   12,00  |   0,0%   |
|    11   |    20    |   0,00   |   8,2%   |
|    21   |    30    |   0,00   |   6,9%   |
|    31   |    40    |   0,00   |   4,7%   |
|    41   |    50    |   0,00   |   1,7%   |

**Observação:** Caso não haja taxa aplicável, lançar um alerta sobre o erro e não permitir transferência.

3 - O usuário deve poder ver o extrato de todos os agendamentos cadastrados.

**Nota:** A persistência deve ser feita em banco de dados em memória H2.
