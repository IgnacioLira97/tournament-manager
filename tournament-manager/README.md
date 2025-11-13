# Pokémon TCG Tournament Manager

This project is a clean Spring Boot + Angular stack for running local tournaments with your friends. The backend keeps track of
tournaments, players, and match outcomes using a simple H2 database, while the Angular UI gives you a dashboard to register people,
run events, and review standings.

## Project structure

```
├── pom.xml                         # Spring Boot backend definition
├── src/main/...                    # REST API, JPA entities, services
└── tournament-manager-frontend/    # Angular 17 single-page app
```

## Requirements

- Java 17+
- Maven 3.9+
- Node.js 18+ (for the Angular dev server)

## Backend: Spring Boot

```bash
mvn spring-boot:run
```

The API will be available at <http://localhost:8080>. Helpful endpoints:

- `POST /api/players` – register a player name once
- `POST /api/tournaments` – create an event (optionally provide a `startDate`)
- `POST /api/matches` – record a result using Pokémon TCG scoring (win = 3, tie = 1)
- `GET /api/tournaments/{id}/standings` – see computed rankings for a tournament

Because the datasource is configured to use `jdbc:h2:file:./data/tournament-db`, data will persist between runs without needing an
external database server. You can also open the H2 console at <http://localhost:8080/h2-console> while the app is running.

## Frontend: Angular

Install packages and run the dev server:

```bash
cd tournament-manager-frontend
npm install
npm start
```

The Angular dev server proxies API calls to `http://localhost:8080` (configure via `proxy.conf.json` if needed). The UI exposes three
panels:

1. Players – register and list all available trainers
2. Tournaments – create tournaments and inspect standings
3. Matches – quickly log the result of a head-to-head match

## Next steps

- Add authentication so guests cannot accidentally edit your tournaments
- Store deck choices and export standings to CSV
- Automate Swiss pairings each round based on the computed standings
