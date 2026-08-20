# Projeto-de-Bloco-Engenharia-de-Softwares-Escal-veis-26E2_5-

---

# TP1

## 📖 Visão Geral
Este projeto consiste em uma aplicação de **Loja de Videogames**, desenvolvida com:
- **Back-end:** Spring Boot (Java 21) + Spring Data JPA + H2 Database
- **Front-end:** React (Vite) + CSS customizado
- **Arquitetura:** CRUD completo para Jogos, Usuários e Compras, com suporte a tema claro/escuro no front-end.

---

## 🏗️ Arquitetura da Solução

### Componentes Principais
- **Back-end**
  - `Controllers`: expõem endpoints REST (`/jogos`, `/usuarios`, `/compras`)
  - `Services`: regras de negócio
  - `Repositories`: acesso ao banco via JPA
  - `Entities`: modelos de dados (`Jogo`, `Usuario`, `Compra`)

- **Front-end**
  - `App.jsx`: navegação entre telas e alternância de tema
  - `Jogos.jsx`: CRUD de jogos
  - `Usuarios.jsx`: CRUD de usuários
  - `Compras.jsx`: CRUD de compras (relacionando usuários e jogos)
  - `App.css`: estilos responsivos, com suporte a tema claro/escuro

---

## 📊 Diagrama de Componentes

```mermaid
flowchart LR
    subgraph Frontend [Front-end React]
        A[App.jsx] --> B[Jogos.jsx]
        A --> C[Usuarios.jsx]
        A --> D[Compras.jsx]
    end

    subgraph Backend [Back-end Spring Boot]
        E[Controllers] --> F[Services]
        F --> G[Repositories]
        G --> H[Entities]
    end

    Frontend <--> Backend
    Backend --> I[(H2 Database)]
```

---

## 📈 Diagrama de Sequência (Exemplo: Cadastro de Compra)

```mermaid
sequenceDiagram
    actor U as Usuário
    participant F as Front-end (Compras.jsx)
    participant C as Controller (Spring Boot)
    participant S as Service
    participant R as Repository
    participant DB as H2 Database

    U->>F: Preenche formulário de compra
    F->>C: POST /compras
    C->>S: Valida dados
    S->>R: Salva Compra
    R->>DB: Insere registro
    DB-->>R: Confirma inserção
    R-->>S: Retorna Compra
    S-->>C: Retorna resposta
    C-->>F: JSON da nova compra
    F-->>U: Atualiza lista de compras
```

---

## 🎨 Design da Aplicação
- **Separação de responsabilidades:**  
  - Back-end cuida da lógica e persistência  
  - Front-end cuida da experiência do usuário
- **Camadas bem definidas:** Controller → Service → Repository → DB
- **CRUD completo:** Jogos, Usuários e Compras podem ser criados, editados e excluídos
- **Tema claro/escuro persistente:** preferências salvas em `localStorage`
- **DevTools:** reinício automático durante desenvolvimento (desativável em produção)

---

## ⚙️ Instruções de Instalação e Execução

### Back-end
1. Clone o repositório
2. Acesse a pasta do back-end
3. Execute:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. O servidor estará disponível em `http://localhost:8080`

### Front-end
1. Acesse a pasta do front-end
2. Instale dependências:
   ```bash
   npm install
   ```
3. Execute:
   ```bash
   npm run dev
   ```
4. A aplicação estará disponível em `http://localhost:5173`

---

## 📹 Vídeo de Apresentação

https://youtu.be/JF-V-zpRuDI

---

# TP2

---

## Principais Alterações no Código

1. Criação de TipoUsuario e Plataforma para formalização dos dados.
2. Criação de consultas personalizadas em com Usuario, Jogo e Compra.
3. Adição de @Transactional para as ações que lidam com compra e venda.
4. Adição de FetchType.LAZY em relacionamentos Many para evitar puxar dados desnecessários.
5. Desenvolvimento do histórico no código.
6. Criação de algumas classes de teste para aumentar a robustez do código.
