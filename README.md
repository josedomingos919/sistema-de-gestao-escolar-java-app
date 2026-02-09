# 🎓 Sistema de Gestão Escolar (Java + JPA + Hibernate)

Sistema desenvolvido para gestão universitária, permitindo o controlo de estudantes, cursos, disciplinas, matrículas, avaliações e utilizadores com diferentes níveis de acesso.

Este projeto foi criado com foco em **boas práticas de arquitetura**, organização do código e simulação de um ambiente real de software académico.

---

## 🚀 Tecnologias Utilizadas

* **Java**
* **JPA (Jakarta Persistence API)**
* **Hibernate**
* **MySQL**
* **Maven**
* **Apache NetBeans**
* **DAO Pattern**
* **DTO**
* **Enum para perfis de acesso**

---

## 🏗️ Arquitetura do Projeto

O sistema segue uma estrutura organizada para facilitar manutenção e escalabilidade:

```
📦 model        -> Entidades do sistema
📦 dao          -> Acesso aos dados (Repository Pattern)
📦 dto          -> Transferência de dados
📦 ui           -> Interface via console
📦 enums        -> Perfis e constantes do sistema
```

👉 Separação clara de responsabilidades, semelhante a sistemas corporativos.

---

## 🔐 Sistema de Autenticação

O sistema possui um **login com controle de permissões por perfil**:

### 👑 Administrador

* Acesso total ao sistema
* Gestão de utilizadores
* Gestão de cursos, disciplinas e professores
* Matrículas e avaliações
* Relatórios

### 🗂 Secretaria

* Gestão de estudantes
* Realização de matrículas
* Emissão de relatórios

### 👨‍🏫 Professor

* Lançamento de avaliações
* Consulta de disciplinas
* Visualização de relatórios

👉 O menu adapta-se automaticamente conforme o perfil autenticado.

---

## 📚 Funcionalidades

✔ Cadastro de cursos
✔ Gestão de estudantes
✔ Controle de disciplinas
✔ Matrículas académicas
✔ Lançamento de notas
✔ Relatórios académicos
✔ Sistema de login
✔ Controle de permissões
✔ Arquitetura organizada

---

## 📊 Exemplos de Relatórios

* Listar estudantes por curso
* Estudantes matriculados em uma disciplina
* Histórico académico simples

---

## ⚙️ Como Executar o Projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

---

### 2️⃣ Configure o banco de dados MySQL

Crie um database e atualize o arquivo:

```
persistence.xml
```

Exemplo:

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/sistema_escolar"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="sua_senha"/>
```

---

### 3️⃣ Instale as dependências Maven

O próprio Maven fará o download automaticamente.

---

### 4️⃣ Execute o projeto

Rode a classe principal:

```
SistemaDeGestaoEscolarJavaApp.java
```

---

## 🎯 Objetivo do Projeto

Este sistema foi desenvolvido para:

✅ Praticar desenvolvimento backend com Java
✅ Aplicar JPA/Hibernate
✅ Trabalhar com arquitetura em camadas
✅ Simular regras de negócio reais
✅ Implementar controle de acesso
✅ Criar um projeto com padrão profissional

---

## 🔥 Possíveis Melhorias Futuras

* Criptografia de senhas (BCrypt)
* Interface gráfica (JavaFX ou Web)
* API REST com Spring Boot
* Paginação de dados
* Logs e auditoria
* Deploy em servidor

---

## 👨‍💻 Autor

**José Domingos Cassua Ndonge**

Desenvolvedor Full Stack com experiência em:

* Java
* Node.js
* PHP
* React

---

## ⭐ Se este projeto te ajudou, não esqueça de dar uma estrela no repositório!
