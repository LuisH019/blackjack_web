Este é o resumo final do projeto, incluindo o `README.md` detalhado e a estrutura de pastas. O código foi totalmente refatorado para alta modularidade, utilizando **SRP**, **DIP** (via interfaces), **Singleton**, **Factory Method** e **Observer**.

-----

# 🃏 Blackjack Web App (Java, Spark & Padrões de Projeto SOLID)

Este projeto implementa um jogo simples de Blackjack (21) em Java utilizando o micro-framework web Spark e o motor de templates Thymeleaf. O design é focado na aplicação estrita de princípios de software como SOLID e Padrões de Projeto (Singleton, Factory Method, Observer, Injeção de Dependência) para garantir alta modularidade e baixo acoplamento.

## 📁 Estrutura do Projeto

A estrutura foi desenhada para isolar a **Lógica de Domínio (`core`)** da **Interface Web (`server`)**.

```
.
├── scripts/
│   ├── build.ps1             # Script para compilar o projeto
│   ├── run.ps1               # Script para executar o JAR
│   └── build-and-run.ps1     # Script para compilar e executar
├── pom.xml                   # Configuração Maven e Dependências
├── README.md                 # Este arquivo
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           ├── Main.java               # Ponto de entrada (Composition Root)
        │           ├── core/                   # CORE: Lógica de Domínio (Alto Reúso)
        │           │   ├── contracts/          # Interfaces (DIP/Observer)
        │           │   ├── factories/          # Padrão Factory Method
        │           │   ├── managers/           # Controladores e Motores de Regras (SRP)
        │           │   └── models/             # Modelos de Domínio
        │           │       ├── decking/        # Deck (Singleton)
        │           │       ├── dtos/           # GameData (DTO para Observer)
        │           │       ├── entities/       # Card, Hand, Rank, Suit (Entidades Fundamentais)
        │           │       └── participants/   # Player, Dealer
        │           └── server/                 # CAMADA WEB (Spark)
        │               └── api/
        │                   └── BlackjackApi.java # Controller Spark (Observer Pattern)
        └── resources/
            └── templates/
                └── index.html            # Template Thymeleaf (View)
```

## ⚙️ Padrões de Design Implementados

| Padrão / Princípio | Aplicação no Projeto | Benefício |
| :--- | :--- | :--- |
| **SRP** (SOLID) | Separado `BlackjackGameManager` (Orquestrador) de `BlackjackRulesEngine` (Regras puras). | O gerente não precisa saber como as regras são aplicadas, apenas que elas existem. |
| **DIP** (SOLID) | `BlackjackGameManager` depende de `IBlackjackRules` e `BlackjackApi` depende de `IGameController`. | Permite trocar o motor de regras ou o controlador de jogo sem modificar a API. |
| **Singleton** | Implementado na classe `Deck`. | Garante que haja apenas uma fonte de cartas embaralhadas para todas as rodadas. |
| **Factory Method** | Implementado em `PlayerFactory`. | Centraliza a lógica de criação de objetos complexos como `Player` e `Dealer`. |
| **Observer** | `BlackjackGameManager` (Subject) notifica `BlackjackApi` (Observer). | A lógica do jogo não tem conhecimento da camada web, enviando o `GameData` de forma desacoplada a cada mudança de estado. |

-----

## 🚀 Como Rodar o Projeto

### Pré-requisitos

Para executar este projeto, você precisará dos seguintes softwares instalados:

1.  **Java Development Kit (JDK) 11 ou superior**
2.  **Apache Maven** (Gerenciador de dependências)
3.  **PowerShell** (Para usar os scripts de conveniência)

### 1\. Compilação (Build)

O projeto utiliza o **Maven Shade Plugin** (`pom.xml`) para criar um JAR executável único que inclui todas as dependências (uber-jar).

#### Opção A: Usando o Script PowerShell

Execute a partir da raiz do projeto:

```powershell
.\scripts\build.ps1
```

#### Opção B: Manualmente

Execute o comando Maven:

```bash
mvn package
```

O arquivo JAR executável será gerado em `target/blackjack-1.0-SNAPSHOT.jar`.

### 2\. Execução

#### Opção A: Usando o Script PowerShell

O script `run.ps1` inicia o JAR compilado.

```powershell
.\scripts\run.ps1
```

#### Opção B: Compilar e Rodar em um Passo

O script `build-and-run.ps1` executa os dois scripts anteriores em sequência.

```powershell
.\scripts\build-and-run.ps1
```

#### Opção C: Manualmente

```bash
java -jar target/blackjack-1.0-SNAPSHOT.jar
```

### Acesso à Aplicação

Após a execução, abra seu navegador e acesse:

**`http://localhost:4567`**

-----

## 🤝 Como Contribuir (GitHub)

Sua contribuição é muito bem-vinda\! Se você deseja adicionar novos recursos, corrigir *bugs* ou melhorar o código (ex: refatorar mais para usar o padrão Decorator), siga os passos abaixo:

1.  **Fork** este repositório para a sua conta GitHub.
2.  Crie uma nova *branch* para sua funcionalidade ou correção:
    ```bash
    git checkout -b feature/minha-melhoria
    ```
3.  Faça suas alterações e garanta que o projeto compile e execute sem erros (usando `mvn package`).
4.  Faça o *commit* de suas alterações:
    ```bash
    git commit -m "feat: Adiciona lógica para Blackjack de Pagamento 3:2"
    ```
5.  Faça o *push* para sua *branch*:
    ```bash
    git push origin feature/minha-melhoria
    ```
6.  Abra um **Pull Request (PR)** na página do repositório original. Descreva claramente as mudanças e o motivo da contribuição.