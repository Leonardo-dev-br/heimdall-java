# 🚀 Projeto HEIMDALL – Sistema de Localização Inteligente de Motocicletas
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
  -    Sensores IoT detectam presença nas vagas.

  - Leitores RFID identificam a moto ocupante.

  - O sistema atualiza um mapa digital em tempo real, acessível via Web ou App.

# Estrutura do projeto
 📁 Estrutura de Pastas 

```
heimdall-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── heimdall/
│   │   │           ├── controller/       # Endpoints da API (camada de apresentação)
│   │   │           ├── service/          # Regras de negócio
│   │   │           ├── model/            # Entidades JPA (Moto, Vaga, etc.)
│   │   │           ├── repository/       # Interfaces de acesso ao banco (JPA)
│   │   │           ├── exception/        # Tratamento global de exceções
│   │   │           ├── config/           # Configurações gerais da aplicação (ex: CORS, Swagger, etc)
│   │   │           ├── dto/              # Data Transfer Objects (entrada/saída da API)
│   │   │           ├── specifications/   # Filtros dinâmicos para consultas (Criteria API)
│   │   │           ├── constants/        # Constantes reutilizáveis na aplicação
│   │   │           └── HeimdallApi.java  # Classe principal (ponto de entrada)
│   │   └── resources/
│   │       ├── application.properties    # Configurações de banco, portas, etc
│   │       ├── static/                   # (Opcional) arquivos estáticos
│   │       └── templates/                # (Opcional) templates HTML (caso use Thymeleaf)
├── pom.xml                               # Gerenciador de dependências Maven
└── README.md                             # Documentação do projeto
```

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Oracle Database
- Swagger (SpringDoc)
- Lombok
- Migrations com flyway
- Maven

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 21 instalado
- Maven 3.8+ instalado
- IDE (IntelliJ, Eclipse, VS Code) — opcional, mas recomendado

---
