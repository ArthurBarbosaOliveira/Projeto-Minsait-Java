# Emprestimo Home

## Decrição

Projeto de cadastro e visualização de clientes e empréstimos.<br>
Esse projeto foi gerado com [Angular CLI](https://github.com/angular/angular-cli) versão 15.2.4.<br>

### Funcionalidades

- Cadastro de clientes
- Edição de clientes
- Exclusão de clientes

Este projeto foi desenvolvido para utilizar como backend o 
[emprestimo-api](https://github.com/andersonhsporto/emprestimo-api) 
e utiliza a rota local http://localhost:8080 para acessar este.


## Tecnologias

- [Angular](https://angular.io/) - Framework para desenvolvimento de aplicações web
- [Typescript](https://www.typescriptlang.org/) - Superset do Javascript
- [Bootstrap](https://getbootstrap.com/) - Framework para desenvolvimento de aplicações web
- [Ngx-currency](https://www.npmjs.com/package/ngx-currency) - Biblioteca para formatação de moeda
- [Sweetalert2](https://sweetalert2.github.io/) - Biblioteca para criação de alertas

## Instalação

Execute `npm install` para instalar as dependências do projeto.

## Servidor de desenvolvimento

Execute `ng serve` para um servidor de desenvolvimento. Navegue para `http://localhost:4200/`. A aplicação será recarregada automaticamente se você alterar qualquer um dos arquivos de origem.

## Gerando código

Execute `ng generate component component-name` para gerar um novo componente. Você também pode usar `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Execute `ng build` para construir o projeto. Os artefatos de construção serão armazenados no diretório `dist/`.

## Executando testes de unidade

Execute `ng test` para executar os testes de unidade via [Karma](https://karma-runner.github.io).

## Executando testes ponta a ponta

Execute `ng e2e` para executar os testes ponta a ponta via uma plataforma de sua escolha. Para usar este comando, você precisa primeiro adicionar um pacote que implemente capacidades de teste ponta a ponta.

## Ajuda adicional

Para obter mais ajuda sobre o Angular CLI, use `ng help` ou vá para a página [Angular CLI Overview and Command Reference](https://angular.io/cli).

## Docker

Para executar o projeto em um container docker, execute o comando `docker-compose up` na raiz do projeto.
Este comando irá criar uma imagem docker com o nome `emprestimo-v1` e executar o container com o nome `client-emprestimo` na porta `3000`.

## Informações de contato

Caso tenha alguma dúvida, sugestão ou crítica, entre em contato comigo pelo [email](mailto:arthurbarbosa93@gmail.com)
ou pelo [LinkedIn](https://www.linkedin.com/in/arthurbarbosaoliveira/).
