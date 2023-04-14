# :bank: Boas vindas ao meu repositório do Projeto-Minsait-Java!! :rocket:
 <summary>
    <strong>👨‍💻 O que deverá ser desenvolvido</strong>
 </summary><br>
Neste projeto, eu desenvolvi um App Rest para gerenciamento de um banco para cadastramento de clientes e empréstimos.  .

# :bar_chart: Tecnologias Utilizadas

* [JAVA 8](https://www.java.com/pt-BR/) - Linguagem de programação (JDK 1.8).
* [Spring](https://spring.io/projects/spring-boot) - Framework MVC.
* [Apache Maven 3.8.6](https://maven.apache.org/) - Gerenciador de dependências.
* [IntelliJ](https://www.jetbrains.com/idea/) - IDE para desenvolvimento.
* [Docker](https://www.docker.com/) - Serviço de virtualização.
* [H2 Database](https://www.h2database.com/html/main.html) - Banco de dados relacional escrito em Java que funciona em memória.


<summary>
    <strong> ⚠️ Configurações mínimas para execução do projeto</strong>
</summary><br />

Para Executar o projeto.

  1. Clone o repositório `Usar link SSH`

- Entre na pasta do repositório que você acabou de clonar:
  * `cd pasta-do-repositório`

  2. Instale as dependências [**Caso existam**]
  *`npm install`

  3. Importe ele como um Project-Maven e execute com o Rune As - Java Application, ou faça o Rune As no com.financiamento/API - ApiApplication.java. 

Na sua máquina você deve ter 

- O `JDK` deve ter versão igual `1.8`
- O `Maven` 
- E uma `IDE` ou editor de sua preferência.


 <summary>
    <strong>👷 Estruturação do projeto</strong>
 </summary><br>

  Para facilitar o entendimento, podemos dividir a aplicação em **2 Fluxos principais**,

- **Fluxo Cliente** que compreende:

  - (1) Cadastrar um cliente, `POST` onde o `BODY` séria:
  
```bash
    `api/v1/financiamento/clientes`
```

```json
{
    "nome": "Arthur Barbosa Oliveira",
     "cpf": "66114269063",
     "email": "arthurbarbosa@gmail.com",
     "telefone": "(11) 92122-5678",
     "rua": "Rua Creusa",
     "numero": "30",
     "cep": "58077-567",
     "rendaMensal": 90000.0
}
```

  - (2) Obter dados dos Clientes `GET`:
  
  ```bash
      `api/v1/financiamento/clientes`
  ```
  
  - (3) Obter os dados de um Cliente com o CPF:
  
  ```bash
      `api/v1/financiamento/clientes/{cpf}`
  ```
  
  - (4) Apaga um cliente `DELET`: 
  
  ```bash
      `api/v1/financiamento/clientes/{cpf}`
  ```
  
  - (5) Atualizar os dados de um cliente `PUT`:
  
  ```bash   
      `api/v1/financiamento/clientes/{cpf}`
  ```

- **Fluxo do Empréstimos** que compreende:

  - (1) Cadastrar um empréstimo, `POST` onde o `BODY` séria:
  
  ```bash
      `/api/v1/clientes/{cpf}/emprestimos`
  ```
  
```json
{
    "cpfCliente": "66114269063",
    "valorInicial": 100.00,
    "dataEmprestimo": "2017-01-13",
    "tipoRelacionamento": "OURO"
}
```

  - (2) Apaga os dados de um empréstimo `DELET`:
  
```bash  
    `/api/v1/clientes/{cpf}/emprestimos`
```

  - (3) Retornar os dados de um empréstimo `GET`:
  
```bash  
    `/api/v1/clientes/{cpf}/emprestimos/{id}`
```

  - (4) Obter a lista dos empréstimos do cliente `GET`:
  
```bash  
    `/api/v1/clientes/{cpf}/emprestimos`
```

- ⚠️ **Importante** ⚠️:
  As dependências são declaradas no arquivo [pom.xml]

 <summary>
    <strong>👨‍💻 Informações de contato</strong>
 </summary><br>

- Seja bem vindo para tirar alguma dúvida, fazer alguma sugestão ou crítica, ou até mesmo bater um papo sobre, segue meus contatos.


[E-mail](mailto:arthurbarbosa93@gmail.com)    [LinkedIn](https://www.linkedin.com/in/arthurbarbosaoliveira/)

