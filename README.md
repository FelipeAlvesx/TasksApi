# 📋 TasksAPI: API de Gerenciamento de Hábitos e Tarefas Pessoais

**TasksAPI** é uma API REST minimalista e segura, desenvolvida em Spring Boot, ideal para registrar tarefas, hábitos e lembretes diários. Este projeto foca em demonstrar o domínio de **segurança (JWT)** e **DevOps Básico (Docker)**.

## 🌟 Visão Geral e Recursos Chave

| Recurso | Descrição | Status |
| :--- | :--- | :--- |
| **Tarefas (CRUD)** | Gerenciamento completo (criar, ler, atualizar, deletar) de registros de hábitos/tarefas. | ✅ Implementado |
| **Autenticação** | Segurança total via **JWT (Auth0)** para proteger todos os *endpoints*. | 🔒 Implementado |
| **Conteinerização** | Pronto para *deploy* rápido via **Dockerfile**. | 🐳 Implementado |
| **Persistência** | Banco de dados **H2** (em memória), ideal para portabilidade. | 💾 Implementado |

## 🛠️ Stack Tecnológica

* **Backend:** Java 17+, Spring Boot 3
* **Dados:** H2 Database, Spring Data JPA
* **Segurança:** Spring Security, JWT (Auth0)
* **Produtividade:** Lombok
* **DevOps:** **Docker**
* **Interação:** Cliente HTTP (Postman/Insomnia)

## 🚀 Guia Rápido de Instalação (Docker)

O método recomendado para rodar o projeto é via Docker, que empacota o ambiente Java e o banco de dados H2, garantindo uma inicialização rápida e isolada.

### Pré-requisitos

1.  **Docker** e **Docker Compose** instalados.
2.  **Git**.
3.  **Cliente HTTP (Postman, Insomnia)** para interagir e testar os *endpoints*.

### 1. Inicialização do Projeto

1.  **Clone e acesse o diretório:**
    ```bash
    git clone [LINK_DO_SEU_REPOSITORIO]
    cd tasks-api
    ```
2.  **Construa a imagem e execute o container:**
    ```bash
    # O Dockerfile já está configurado para empacotar a aplicação
    docker build -t tasks-api .
    docker run -p 8080:8080 tasks-api
    ```

A API estará rodando na porta `8080` do seu host.

## 🔒 Interagindo com a API

Devido à segurança implementada, todas as operações de CRUD de tarefas exigem um token de autenticação.

### 1. Login (Obter JWT)

Use seu **Cliente HTTP** para obter o token de acesso:

* **Método:** `POST`
* **URL:** `http://localhost:8080/login`
* **Body (JSON Exemplo):**
    ```json
    {
        "login": "seu_usuario",
        "password": "sua_senha"
    }
    ```
    *A resposta retornará o seu `token` JWT.*

### 2. Requisições Protegidas

Use o token retornado no cabeçalho **Authorization** para todas as requisições subsequentes:

| Cabeçalho | Valor |
| :--- | :--- |
| `Authorization` | `Bearer <SEU_TOKEN_JWT>` |

### 📚 Exemplo de Uso (Criar Tarefa)

* **Método:** `POST`
* **URL:** `http://localhost:8080/tasks`
* **Headers:** `Authorization: Bearer <SEU_TOKEN>`
* **Body (JSON Exemplo):**
    ```json
    {
        "titulo": "Finalizar documentação",
        "descricao": "Revisar o README e adicionar todos os detalhes.",
        "concluida": false
    }
    ```

## 🔮 Próximos Passos e Melhorias

* **Persistência Real:** Migrar o banco H2 para **PostgreSQL** ou **MySQL**, implementando **Docker Compose** para orquestrar o banco e a API.
* **Testes de Unidade:** Adicionar cobertura de testes para a lógica de negócio principal (JUnit e Mockito).
* **Filtros Avançados:** Implementar filtros de busca e ordenação para tarefas (ex: filtrar por status: `concluída`, `pendente`).
