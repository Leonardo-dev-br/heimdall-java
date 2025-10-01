# 🚀 Projeto HEIMDALL – Sistema de Localização Inteligente de Motocicletas

## 📘 Descrição Geral

Este projeto foi desenvolvido como parte de um desafio proposto pela faculdade em parceria com a Startup Mottu, que atua no ramo de aluguel de motocicletas, especialmente voltado para entregadores de aplicativos. A proposta surgiu a partir de uma dor real enfrentada pela empresa: a dificuldade na gestão e localização eficiente das motocicletas dentro de seu pátio físico.

Com isso, desenvolvemos uma API Java para o sistema HEIMDALL, uma solução inovadora baseada em tecnologias IoT e RFID, com o objetivo de automatizar o controle, rastreamento e organização das motos no pátio.

## 🔍 Problema Identificado

Conforme a frota da Mottu cresce, o processo manual de identificação e entrega das motocicletas tem se tornado lento, ineficiente e sujeito a erros. Entre eles:

* Tempo excessivo em horários de pico;
* Alta chance de falhas humanas;
* Desorganização do espaço físico;
* Retrabalho da equipe de logística;
* Dificuldade em escalar o serviço.

## 💡 A Ideia do Projeto

Pensando nisso, idealizamos uma solução tecnológica que modernize e otimize esse processo, garantindo:

* Localização em tempo real das motos no pátio.
* Redução drástica do tempo de busca por veículos.
* Menor ocorrência de erros humanos durante entregas e devoluções.
* Aumento da eficiência operacional e da experiência do cliente.

# 🛠️ Solução Proposta – HEIMDALL

O HEIMDALL é um Sistema de Mapeamento e Monitoramento Inteligente de Vagas, que integra sensores de presença e leitores RFID para rastrear a ocupação e localização das motocicletas dentro do pátio em tempo real.

## Componentes da Solução

* 🗂️ Zonamento Físico

  O pátio é dividido por tipo e modelo das motocicletas:

  * ZC1: Zona de Combustão 1 – Mottu-Sport
  * ZC2: Zona de Combustão 2 – Mottu-Pop
  * ZE: Zona Elétrica – Motos elétricas
* 🏷️ Identificação das Vagas

  Cada vaga recebe um identificador único, ex:

  * ZC1VG1 → Zona de Combustão 1, Vaga 1
* 📡 Monitoramento IoT + RFID

  * Sensores IoT detectam presença nas vagas.
  * Leitores RFID identificam a moto ocupante.
  * O sistema atualiza um mapa digital em tempo real, acessível via Web ou App.

---

## ⚙️ Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Security (autenticação e autorização)
* Thymeleaf (páginas de login/registro)
* Oracle Database
* Swagger (SpringDoc)
* Lombok
* Migrations com Flyway
* Maven

---

## 🔐 Autenticação e Login

O projeto utiliza **Spring Security** integrado ao **Thymeleaf** para controle de autenticação.

* Página de login: `/login`
* Endpoint de autenticação: `POST /login` (interceptado pelo filtro do Spring Security)
* Página de cadastro: `/register`
* Após login bem-sucedido → redireciona para `/histories`

### ⚙️ Usuário Inicial (Flyway Seed)

Na primeira execução, o Flyway cria automaticamente um usuário **administrador padrão** para acesso inicial ao sistema:

* **E-mail:** `dimebag@hotmail.com`
* **Senha:** `password` (armazenada como hash `BCrypt`)

> ⚠️ Recomenda-se alterar a senha após o primeiro acesso.

---

## 🌐 Deploy

O sistema está hospedado e disponível publicamente em:

[https://heimdall-java.onrender.com/](https://heimdall-java.onrender.com/)

> Para rodar localmente, utilize as instruções abaixo.

---

## 📁 Estrutura de Pastas

<pre class="overflow-visible!" data-start="3488" data-end="4886"><div class="contain-inline-size rounded-2xl relative bg-token-sidebar-surface-primary"><div class="sticky top-9"><div class="absolute end-0 bottom-0 flex h-9 items-center pe-2"><div class="bg-token-bg-elevated-secondary text-token-text-secondary flex items-center gap-4 rounded-sm px-2 font-sans text-xs"></div></div></div><div class="overflow-y-auto p-4" dir="ltr"><code class="whitespace-pre!"><span><span>heimdall-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── heimdall/
│   │   │           ├── controller/                </span><span># Endpoints da API (camada de apresentação)</span><span>
│   │   │           ├── service/                   </span><span># Regras de negócio</span><span>
│   │   │           ├── model/                     </span><span># Entidades JPA (Moto, Vaga, etc.)</span><span>
│   │   │           ├── repository/                </span><span># Interfaces de acesso ao banco (JPA)</span><span>
│   │   │           ├── exception/                 </span><span># Tratamento global de exceções</span><span>
│   │   │           ├── config/                    </span><span># Configurações gerais (CORS, Security, Swagger, etc)</span><span>
│   │   │           ├── dto/                       </span><span># Data Transfer Objects (entrada/saída da API)</span><span>
│   │   │           ├── specifications/            </span><span># Filtros dinâmicos para consultas (Criteria API)</span><span>
│   │   │           ├── constants/                 </span><span># Constantes reutilizáveis</span><span>
│   │   │           └── HeimdallApplication.java   </span><span># Classe principal (ponto de entrada)</span><span>
│   │   └── resources/
│   │       ├── application.properties             </span><span># Configurações (DB, portas, etc)</span><span>
│   │       └── db/
│   │           └── migrations/                    </span><span># Scripts de criação e seed do banco (Flyway)</span><span>
├── pom.xml                                        </span><span># Gerenciador de dependências Maven</span><span>
└── README.md                                      </span><span># Documentação do projeto</span><span>
</span></span></code></div></div></pre>

---

## 🧭 Endpoints da API

### 🏍️ Motos (`/motorcycles`)


| Método | Endpoint          | Descrição                                      |
| --------- | ------------------- | -------------------------------------------------- |
| GET     | /motorcycles      | Lista todas as motos (com filtros e paginação) |
| GET     | /motorcycles/{id} | Retorna uma moto específica por ID              |
| POST    | /motorcycles      | Cadastra uma nova moto                           |
| PUT     | /motorcycles/{id} | Atualiza os dados de uma moto                    |
| DELETE  | /motorcycles/{id} | Remove uma moto pelo ID                          |

---

### 🏷️ Tags RFID (`/tags`)


| Método | Endpoint   | Descrição                                     |
| --------- | ------------ | ------------------------------------------------- |
| GET     | /tags      | Lista todas as tags (com filtros e paginação) |
| GET     | /tags/{id} | Retorna uma tag específica por ID              |
| POST    | /tags      | Cadastra uma nova tag RFID                      |
| PUT     | /tags/{id} | Atualiza os dados de uma tag RFID               |
| DELETE  | /tags/{id} | Remove uma tag pelo ID                          |

---

### 👤 Usuários (`/users`)


| Método | Endpoint    | Descrição                                          |
| --------- | ------------- | ------------------------------------------------------ |
| GET     | /users      | Lista todos os usuários (com filtros e paginação) |
| GET     | /users/{id} | Retorna um usuário específico por ID               |
| POST    | /users      | Cadastra um novo usuário                            |
| PUT     | /users/{id} | Atualiza os dados de um usuário                     |
| DELETE  | /users/{id} | Remove um usuário pelo ID                           |

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos

* Java 21 instalado
* Maven 3.8+ instalado
* IDE (IntelliJ, Eclipse, VS Code) — opcional, mas recomendado
* Banco de Dados Oracle configurado

---

### ⚙️ Configuração do Banco de Dados

Antes de rodar a aplicação, configure sua conexão no arquivo:

<pre class="overflow-visible!" data-start="6954" data-end="7003"><div class="contain-inline-size rounded-2xl relative bg-token-sidebar-surface-primary"><div class="sticky top-9"><div class="absolute end-0 bottom-0 flex h-9 items-center pe-2"><div class="bg-token-bg-elevated-secondary text-token-text-secondary flex items-center gap-4 rounded-sm px-2 font-sans text-xs"></div></div></div><div class="overflow-y-auto p-4" dir="ltr"><code class="whitespace-pre!"><span><span>src</span><span>/</span><span>main</span><span>/resources/application</span><span>.properties</span><span>
</span></span></code></div></div></pre>

Com as variáveis:

* `spring.datasource.url`
* `spring.datasource.username`
* `spring.datasource.password`

---

### ▶️ Rodando o projeto

<pre class="overflow-visible!" data-start="7149" data-end="7363"><div class="contain-inline-size rounded-2xl relative bg-token-sidebar-surface-primary"><div class="sticky top-9"><div class="absolute end-0 bottom-0 flex h-9 items-center pe-2"><div class="bg-token-bg-elevated-secondary text-token-text-secondary flex items-center gap-4 rounded-sm px-2 font-sans text-xs"></div></div></div><div class="overflow-y-auto p-4" dir="ltr"><code class="whitespace-pre! language-bash"><span><span># Clone o repositório</span><span>
git </span><span>clone</span><span> https://github.com/Leonardo-dev-br/heimdall-java.git

</span><span># Acesse o diretório</span><span>
</span><span>cd</span><span> heimdall-java

</span><span># Compile o projeto</span><span>
mvn clean install

</span><span># Rode a aplicação</span><span>
mvn spring-boot:run
</span></span></code></div></div></pre>

A API estará disponível em:

<pre class="overflow-visible!" data-start="7394" data-end="7423"><div class="contain-inline-size rounded-2xl relative bg-token-sidebar-surface-primary"><div class="sticky top-9"><div class="absolute end-0 bottom-0 flex h-9 items-center pe-2"><div class="bg-token-bg-elevated-secondary text-token-text-secondary flex items-center gap-4 rounded-sm px-2 font-sans text-xs"></div></div></div><div class="overflow-y-auto p-4" dir="ltr"><code class="whitespace-pre!"><span><span>http:</span><span>//localhost:8080</span><span>
</span></span></code></div></div></pre>

---

## 👥 Integrantes


| Nome Completo              | RM     |
| ---------------------------- | -------- |
| Pedro Henrique Lima Santos | 558243 |
| Vitor Gomes Martins        | 558244 |
| Leonardo Pimentel Santos   | 557541 |

## 📘 Descrição Geral

Este projeto foi desenvolvido como parte de um desafio proposto pela faculdade em parceria com a Startup Mottu, que atua no ramo de aluguel de motocicletas, especialmente voltado para entregadores de aplicativos. A proposta surgiu a partir de uma dor real enfrentada pela empresa: a dificuldade na gestão e localização eficiente das motocicletas dentro de seu pátio físico.

Com isso, desenvolvemos uma API Java para o sistema HEIMDALL, uma solução inovadora baseada em tecnologias IoT e RFID, com o objetivo de automatizar o controle, rastreamento e organização das motos no pátio.

## 🔍 Problema Identificado

Conforme a frota da Mottu cresce, o processo manual de identificação e entrega das motocicletas tem se tornado lento, ineficiente e sujeito a erros. Entre eles:

- Tempo excessivo em horários de pico;
- Alta chance de falhas humanas;
- Desorganização do espaço físico;
- Retrabalho da equipe de logística;
- Dificuldade em escalar o serviço.

## 💡 A Ideia do Projeto

Pensando nisso, idealizamos uma solução tecnológica que modernize e otimize esse processo, garantindo:

- Localização em tempo real das motos no pátio.
- Redução drástica do tempo de busca por veículos.
- Menor ocorrência de erros humanos durante entregas e devoluções.
- Aumento da eficiência operacional e da experiência do cliente.

# 🛠️ Solução Proposta – HEIMDALL

O HEIMDALL é um Sistema de Mapeamento e Monitoramento Inteligente de Vagas, que integra sensores de presença e leitores RFID para rastrear a ocupação e localização das motocicletas dentro do pátio em tempo real.

## Componentes da Solução

- 🗂️ Zonamento Físico

  - O pátio é dividido por tipo e modelo das motocicletas:
  - ZC1: Zona de Combustão 1 – Mottu-Sport
  - ZC2: Zona de Combustão 2 – Mottu-Pop
  - ZE: Zona Elétrica – Motos elétricas
- 🏷️ Identificação das Vagas

  - Cada vaga recebe um identificador único, ex:
    - ZC1VG1 → Zona de Combustão 1, Vaga 1
- 📡 Monitoramento IoT + RFID

  - Sensores IoT detectam presença nas vagas.
  - Leitores RFID identificam a moto ocupante.
  - O sistema atualiza um mapa digital em tempo real, acessível via Web ou App.

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Oracle Database
- Swagger (SpringDoc)
- Lombok
- Migrations com flyway
- Maven

## Estrutura do projeto

### 📁 Estrutura de Pastas

```
heimdall-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── heimdall/
│   │   │           ├── controller/                # Endpoints da API (camada de apresentação)
│   │   │           ├── service/                   # Regras de negócio
│   │   │           ├── model/                     # Entidades JPA (Moto, Vaga, etc.)
│   │   │           ├── repository/                # Interfaces de acesso ao banco (JPA)
│   │   │           ├── exception/                 # Tratamento global de exceções
│   │   │           ├── config/                    # Configurações gerais da aplicação (ex: CORS, Swagger, etc)
│   │   │           ├── dto/                       # Data Transfer Objects (entrada/saída da API)
│   │   │           ├── specifications/            # Filtros dinâmicos para consultas (Criteria API)
│   │   │           ├── constants/                 # Constantes reutilizáveis na aplicação
│   │   │           └── HeimdallApplication.java   # Classe principal (ponto de entrada)
│   │   └── resources/
│   │       ├── application.properties             # Configurações de banco, portas, etc
│   │       └── db
│   │           └── migrations/                    # Contem arquivos para criação de tabelas do banco de dados        
├── pom.xml                                        # Gerenciador de dependências Maven
└── README.md                                      # Documentação do projeto
```

## 🧭 Endpoints da API

### 🏍️ Motos (`/motorcycles`)


| Método | Endpoint          | Descrição                                      |
| --------- | ------------------- | -------------------------------------------------- |
| GET     | /motorcycles      | Lista todas as motos (com filtros e paginação) |
| GET     | /motorcycles/{id} | Retorna uma moto específica por ID              |
| POST    | /motorcycles      | Cadastra uma nova moto                           |
| PUT     | /motorcycles/{id} | Atualiza os dados de uma moto                    |
| DELETE  | /motorcycles/{id} | Remove uma moto pelo ID                          |

---

### 🏷️ Tags RFID (`/tags`)


| Método | Endpoint   | Descrição                                     |
| --------- | ------------ | ------------------------------------------------- |
| GET     | /tags      | Lista todas as tags (com filtros e paginação) |
| GET     | /tags/{id} | Retorna uma tag específica por ID              |
| POST    | /tags      | Cadastra uma nova tag RFID                      |
| PUT     | /tags/{id} | Atualiza os dados de uma tag RFID               |
| DELETE  | /tags/{id} | Remove uma tag pelo ID                          |

---

### 👤 Usuários (`/users`)


| Método | Endpoint    | Descrição                                          |
| --------- | ------------- | ------------------------------------------------------ |
| GET     | /users      | Lista todos os usuários (com filtros e paginação) |
| GET     | /users/{id} | Retorna um usuário específico por ID               |
| POST    | /users      | Cadastra um novo usuário                            |
| PUT     | /users/{id} | Atualiza os dados de um usuário                     |
| DELETE  | /users/{id} | Remove um usuário pelo ID                           |

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 21 instalado
- Maven 3.8+ instalado
- IDE (IntelliJ, Eclipse, VS Code) — opcional, mas recomendado
- Banco de Dados Oracle configurado

---

### ⚙️ Configuração do Banco de Dados

Antes de rodar a aplicação, configure sua conexão com o banco de dados, colocando seu usuario e senha do banco de dados Oracle no arquivo:

```
src/main/resources/application.properties
```

### Rodando o projeto

```bash
# Clone o repositório
git clone https://github.com/Leonardo-dev-br/heimdall-java.git

# Acesse o diretório
cd heimdall-java

# Compile o projeto
mvn clean install

# Rode a aplicação
mvn spring-boot:run
```

A API estará disponível em:

```
http://localhost:8080
```

## Integrantes


| Nome Completo              | RM     |
| ---------------------------- | -------- |
| Pedro Henrique Lima Santos | 558243 |
| Vitor Gomes Martins        | 558244 |
| Leonardo Pimentel Santos   | 557541 |
