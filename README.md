# API Watchlist

API REST simples feita com **Spring Boot** para gerenciar uma lista de filmes, séries e animes (o que você quer assistir, está assistindo ou já assistiu).

Baseada na arquitetura em camadas do projeto [API-Playlist](https://github.com/jcfdantas/API-Playlist).

## Estrutura

```
controller  -> recebe a requisição HTTP e devolve a resposta
service     -> regras de negócio (404, só dá nota se já assistiu...)
repository  -> acesso ao banco (JpaRepository)
model       -> entidade Titulo + enums (TipoTitulo, StatusTitulo)
dto         -> TituloDTO (o que entra/sai da API, com validações)
mapper      -> converte Titulo <-> TituloDTO
```

## Como rodar

Precisa do **Java 21**. Banco H2 em memória (não precisa instalar nada, mas os dados somem ao parar a API).

- **IntelliJ:** abra a pasta, espere o Maven carregar e rode a classe `WatchlistApplication`.
- **Terminal** (com Maven instalado): `mvn spring-boot:run`

A API sobe em http://localhost:8080

## Endpoints

| Método | Rota | O que faz |
|--------|------|-----------|
| GET | `/api/titulos` | Lista tudo (aceita `?status=ASSISTIDO` ou `?busca=matrix`) |
| GET | `/api/titulos/{id}` | Busca um título |
| POST | `/api/titulos` | Cadastra um título |
| PUT | `/api/titulos/{id}` | Atualiza um título |
| PATCH | `/api/titulos/{id}/assistido?nota=9` | Marca como assistido (nota é opcional) |
| DELETE | `/api/titulos/{id}` | Remove um título |

Valores aceitos: `tipo` = `FILME`, `SERIE`, `ANIME` | `status` = `QUERO_ASSISTIR` (padrão), `ASSISTINDO`, `ASSISTIDO`

Regra: só é possível ter `nota` (0 a 10) em títulos `ASSISTIDO`.

## Exemplos (curl)

```bash
# cadastrar
curl -X POST http://localhost:8080/api/titulos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Interestelar","tipo":"FILME","genero":"Ficção científica","ano":2014}'

# listar só o que quero assistir
curl "http://localhost:8080/api/titulos?status=QUERO_ASSISTIR"

# marcar como assistido com nota 10
curl -X PATCH "http://localhost:8080/api/titulos/1/assistido?nota=10"
```

Console do banco: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:watchlist`, user `sa`, senha vazia)
