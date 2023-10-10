# Aplicação para Gerenciamento de Biblioteca
## Desafio
Criação de uma API REST em Java, utilizando Spring Boot e Maven para gerenciar uma biblioteca.

## Endpoints:

### Usuário:

#### Criar Registro de Usuario:
- POST: /usuario

Exemplo de Body:
```json
{
    "nome": "Ragnar Lodbrok",
    "cpf": "12345678910"
}
```

#### Buscar usuario por ID:
- GET: /usuario/{id}
    
#### Buscar usuario por nome:
- GET: /usuario?nome={nome}

#### Editar registro de usuario:
- PUT: /usuario/{id}

#### Deletar registro de usuario:
- DELETE: /usuario/{id}

### Livro:


#### Criar Registro de Livro:
- POST: /livro

Exemplo de Body:
```json
{
    "autor": "Haukr Erlendsson ",
    "titulo": "As Histórias de Ragnar Lodbrok"
}
```

#### Buscar livro por ID:
- GET: /livro/{id}
    
#### Buscar livros do Autor:
- GET: /livro/autor?autor={nome}

#### Buscar livro por título:
- GET: /livro/titulo?titulo={titulo}

#### Editar registro de livro:
- PUT: /livro/{id}

#### Deletar registro de livro:
- DELETE: /livro/{id}

#### Emprestar livro:
- POST: /livro/{id}/emprestimo

Este método permite realizar o empréstimo de um livro para um usuário que terá seu ID informado no body da requisição.

Exemplo de Body:
```json
{
    "id": "12"
}
```

#### Devolver livro:
- POST: /livro/{id}/devolucao

## Executando o programa:

Após clonar o projeto para a máquina, importar o projeto na IDE de sua preferência e executar através da classe inicial, BibliotecaAppAplication.
