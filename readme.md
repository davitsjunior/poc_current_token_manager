# 🛡️ POC - Gerenciamento de Tokens com Keycloak

Esta prova de conceito (POC) demonstra o gerenciamento centralizado de tokens OAuth2, utilizando **Keycloak** como 
provedor de identidade, **Feign Client** para autenticação e **Thymeleaf** para visualização dos tokens em tempo real.

---

## 🔧 Tecnologias Utilizadas

- Java 21 + Spring Boot
- Feign Client
- Thymeleaf
- Keycloak (via Docker)
- ConcurrentHashMap (cache in-memory)
- Script CLI para configuração automatizada do Keycloak

---

## ▶️ Como Executar o Projeto

### 1️⃣ Clone o Repositório

Clone o projeto em sua máquina local.

### 2️⃣ Suba o Keycloak com Docker

Certifique-se de que o Docker esteja instalado e em execução. Em seguida, execute o arquivo `docker-compose.yml` 
que está no projeto para iniciar o Keycloak na porta `8080`, com as credenciais `admin` / `admin`.

### 3️⃣ Configure o Keycloak via Script

Após o container do Keycloak estar rodando, execute o script de configuração que se encontra em:

```
/opt/keycloak/data/import/configure-keycloak.sh
```

Use o seguinte comando:

```
docker exec -it keycloak bash /opt/keycloak/data/import/configure-keycloak.sh
```

Esse script irá:

- Criar o Realm `tokenManagement`
- Criar o Client `clientIdTest`
- Atribuir o Client Secret `clientSecretTest`

### 4️⃣ Execute a Aplicação Spring Boot

Com o Keycloak configurado, execute a aplicação pelo terminal ou por sua IDE.

Certifique-se de que a aplicação esteja configurada para subir na porta `8282`.

### 5️⃣ Acesse o Dashboard

No navegador, vá até:

```
http://localhost:8282/token/tokens/view
```

Você verá um painel com os tokens gerenciados dinamicamente para dois serviços distintos.

---

## 🧪 Resultado Esperado

- Exibição de tokens categorizados por nome e serviço
- Exibição do hash de instância dos tokens, categorizados por nome e serviço. Quando o mesmo hash aparece em serviços 
  diferentes, isso indica que ambos compartilham a mesma instância de token, evidenciando o reaproveitamento.
- Visualização do status do token (`VALID`, `EXPIRED`, etc.)
- Barra de progresso indicando o tempo restante de expiração
- Atualização automática dos tokens via background thread

---

## 📌 Observações

- A primeira chamada gera o token de forma síncrona caso não exista.
- As chamadas subsequentes utilizam cache (`ConcurrentHashMap`).
- Requisições concorrentes são tratadas com controle de status e retorno seguro mesmo em falhas.
- O painel utiliza Thymeleaf para renderizar os dados da aplicação de forma dinâmica.

---

## 👨‍💻 Autor

Desenvolvido por **Davi Junior**  
