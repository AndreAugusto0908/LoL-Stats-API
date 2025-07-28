# 📊 LoL Stats API

API backend para consumo e organização de dados da API oficial do League of Legends, com foco em futuras integrações com um front-end de visualização de dados de jogadores, partidas e campeões.

---

## 📌 Funcionalidades

### 🔍 1. Buscar dados de invocador (Summoner)
- Buscar informações de um jogador pelo nome de invocador.
- Retorna: `summonerId`, `puuid`, `level`, `profileIconId`, `nome`, `região`.

### 🧾 2. Buscar histórico de partidas
- Retorna as últimas partidas de um jogador.
- Parâmetro opcional para limitar a quantidade de partidas.
- Dados retornados por partida:
  - Data
  - Modo de jogo
  - Resultado (vitória/derrota)
  - Campeão utilizado
  - KDA
  - Duração

### 📊 3. Estatísticas agregadas do jogador
- Total de partidas jogadas
- Porcentagem de vitórias
- Campeões mais utilizados
- Média de KDA

### 🏆 4. Ranqueamento (Ranked)
- Informações da fila Solo/Duo e Flex:
  - Tier e Divisão (ex: Platinum IV)
  - Pontos (LP)
  - Taxa de vitória

### 🦸‍♂️ 5. Listagem de campeões
- Retorna todos os campeões do jogo com:
  - Nome
  - Descrição
  - Função
  - Imagem
  - Habilidades

### 🕹️ 6. Detalhes de partida específica
- Buscar detalhes completos de uma partida pelo `matchId`.
- Inclui estatísticas de todos os jogadores da partida.

### 🌍 7. Suporte a múltiplas regiões
- A API aceita um parâmetro de região (`br1`, `na1`, `euw1`, etc.).
- Todas as rotas que consultam a Riot API respeitam essa configuração.

### 🛡️ 8. Autenticação (planejado)
- Preparada para suporte futuro a autenticação via JWT.
- Permitirá recursos como favoritos, perfis salvos e configurações de usuário.

### 💾 9. Cache inteligente
- Implementação de cache para reduzir requisições repetidas à Riot API.
- Tempo sugerido: 10 minutos para histórico e dados públicos.

### 📈 10. Endpoint de status
- Rota `/status` para indicar se a API está operacional.
- Exibe:
  - Uptime
  - Versão atual da API
  - Uso de cota da Riot API

---

## 🚧 Status do Projeto

> Em desenvolvimento inicial. Estruturação da API e definição de rotas principais em andamento.

---

## 🔐 Requisitos de Autenticação da Riot

Para utilizar a Riot API, você precisará de uma **API Key** válida.

- [Painel de desenvolvedor Riot](https://developer.riotgames.com/)
- Lembre-se: a API Key de desenvolvimento expira a cada 24h.

---

## 🚀 Dependencias Utilizadas

✅ Spring Web
→ Para criar os endpoints REST.

✅ Spring Reactive Web (WebFlux)
→ Para usar WebClient (mais moderno que RestTemplate para chamadas HTTP).

✅ Spring Boot DevTools
→ Para hot reload durante o desenvolvimento.

✅ Spring Cache Abstraction (Spring Cache)
→ Para adicionar cache com @Cacheable.

✅ Lombok
→ Para reduzir boilerplate (getters, setters, construtores).

✅ Spring Data Redis
→ Se for usar Redis como cache externo (pode deixar pra depois se preferir).

✅ Spring Configuration Processor
→ Para facilitar o autocomplete de propriedades no application.properties.
---

## 📄 Licença

MIT License
