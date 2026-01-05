# Jogo da Velha com Minimax 🎮

Jogo da velha implementado em Java, com jogabilidade pelo terminal.  Possui um bot imbatível controlado por um algoritmo Minimax.

## 📋 Descrição do Projeto

Este é um jogo da velha (tic-tac-toe) desenvolvido em Java com interface de terminal, oferecendo duas modalidades de jogo:

1. **Jogador vs Jogador (PVP)**: Dois jogadores humanos competem entre si
2. **Jogador vs Máquina (PVE)**: Um jogador enfrenta um bot imbatível

O diferencial do projeto é a implementação de um **bot inteligente** que utiliza o algoritmo Minimax, garantindo que ele **nunca perde** - apenas empata ou vence!

### ✨ Funcionalidades

- ✅ Dois modos de jogo (PVP e PVE)
- ✅ Escolha de símbolo (X ou O)
- ✅ Randomização de quem começa cada partida
- ✅ Histórico de jogadas ao final da partida
- ✅ Opção de jogar múltiplas partidas consecutivas
- ✅ Validação de entradas e tratamento de erros
- ✅ Bot imbatível usando algoritmo Minimax

## 🚀 Como Instalar/Usar

### Pré-requisitos

- **Java JDK 17** ou superior
- **Maven** (para gerenciamento de dependências e build)
- Terminal/Console

### Compilação com Maven

```bash
mvn clean install
```

Isso gerará um arquivo JAR executável na pasta `target/`.

### Execução

```bash
java -jar target/OOex2JogoDaVelhaColecoes-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Como Jogar

1. **Inicie o programa** - Você verá o menu principal
2. **Escolha o modo de jogo**:
   - `[1]` - Jogador VS Jogador
   - `[2]` - Jogador VS Máquina
3. **Configure o jogo**:
   - Digite o nome do jogador
   - Escolha seu símbolo (X ou O)
4. **Faça suas jogadas**:
   - Digite a posição no formato:  `<linha> <coluna>`
   - Exemplo: `0 2` (linha 0, coluna 2)
   - Coordenadas válidas: 0, 1 ou 2
5. **Veja o resultado**:
   - O jogo mostra o tabuleiro após cada jogada
   - Ao final, exibe o histórico completo de jogadas
6. **Jogue novamente**:  Escolha `[S]` para continuar ou `[N]` para sair

**Exemplo de tabuleiro:**
```
  0 1 2 
0 X|O| 
  -----
1  |X|O
  -----
2  | |X
```

## 🧠 Algoritmo Minimax

O bot imbatível utiliza o **algoritmo Minimax**, uma técnica clássica de inteligência artificial para jogos de soma zero. 

### Como Funciona

O Minimax é um algoritmo recursivo que: 

1. **Simula todas as jogadas possíveis** a partir do estado atual do tabuleiro
2. **Cria uma árvore de decisões** com todas as sequências de jogadas até o fim do jogo
3. **Avalia cada estado final** do tabuleiro: 
   - **+10** se o bot vencer (menos a profundidade, para priorizar vitórias rápidas)
   - **-10** se o jogador vencer (mais a profundidade, para adiar derrotas)
   - **0** em caso de empate
4. **Escolhe a melhor jogada** usando dois princípios:
   - **Maximizar**: O bot busca a jogada com maior valor (melhor para ele)
   - **Minimizar**:  Assume que o oponente fará a melhor jogada possível (pior para o bot)

### Por que é Imbatível? 

O algoritmo explora **todas as possibilidades** do jogo.  Como o jogo da velha tem um espaço de estados limitado, o Minimax consegue: 
- Prever todos os resultados possíveis
- Sempre escolher a jogada que garante no mínimo um empate
- Aproveitar qualquer erro do adversário para vencer

### Implementação no Projeto

```java
// Método recursivo que avalia todas as jogadas
private int minimax(TabuleiroPlus tabuleiro, int depth, boolean isMaximizing) {
    if(tabuleiro.terminou())
        return tabuleiro.evaluate(depth);
    
    if(isMaximizing) {
        // Bot busca maximizar o valor
        int maxEva = Integer.MIN_VALUE;
        for(TabuleiroPlus tab : tabuleiro.tabsPossiveis()) {
            int eva = minimax(tab, depth+1, false);
            maxEva = Integer.max(maxEva, eva);
        }
        return maxEva;
    } else {
        // Jogador busca minimizar o valor
        int minEva = Integer.MAX_VALUE;
        for(TabuleiroPlus tab :  tabuleiro.tabsPossiveis()) {
            int eva = minimax(tab, depth+1, true);
            minEva = Integer.min(minEva, eva);
        }
        return minEva;
    }
}
```

**Arquivos relacionados:**
- `src/main/java/botImbativel/Bot.java` - Implementação do bot
- `src/main/java/botImbativel/TabuleiroPlus.java` - Tabuleiro estendido com métodos para Minimax

## 🛠️ Tecnologias Utilizadas

- **Java 17**:  Linguagem de programação principal
- **Maven**: Gerenciamento de dependências e build
- **Terminal/Console**: Interface do usuário
- **Algoritmo Minimax**: IA para o bot imbatível

## 📁 Estrutura do Projeto

```
jogodavelha-minimax/
├── pom.xml                                    # Configuração Maven
├── src/
│   └── main/
│       └── java/
│           ├── OOex2JogoDaVelhaColecoes.java # Classe principal (main)
│           ├── components/                    # Componentes básicos
│           │   ├── Jogador.java              # Classe do jogador
│           │   ├── Tabuleiro.java            # Classe do tabuleiro
│           │   └── Posicao.java              # Classe para coordenadas
│           ├── jogos/                         # Modos de jogo
│           │   ├── Jogo.java                 # Classe abstrata base
│           │   ├── JogoPVP.java              # Modo jogador vs jogador
│           │   └── JogoPVE.java              # Modo jogador vs bot
│           └── botImbativel/                  # Implementação do bot
│               ├── Bot.java                   # Bot com Minimax
│               └── TabuleiroPlus.java         # Tabuleiro estendido
└── target/                                    # Arquivos compilados (gerado)
```

## 🎯 Arquitetura

O projeto utiliza **Programação Orientada a Objetos** com:

- **Herança**: `Bot` estende `Jogador`, `TabuleiroPlus` estende `Tabuleiro`
- **Polimorfismo**: Classe abstrata `Jogo` com implementações `JogoPVP` e `JogoPVE`
- **Encapsulamento**:  Métodos privados para validação e lógica interna
- **Coleções**: Uso de `List<>` e `ArrayList<>` para gerenciar posições e estados

---

⭐ Desenvolvido por [filipemvidal](https://github.com/filipemvidal)
