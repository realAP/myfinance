[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/summary/new_code?id=realAP_myfinance)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=realAP_myfinance)](https://sonarcloud.io/summary/new_code?id=realAP_myfinance)
[![Pipeline](https://github.com/realAP/myfinance/actions/workflows/maven.yml/badge.svg)](https://github.com/realAP/myfinance/actions/workflows/maven.yml)

# MyFinance

TO BE DONE...

## Umgebungsvariablen

Diese Variablen verlangt eine Stage. Ohne sie startet der Stack nicht.

### Anwendung

| Variable | Dienst | Bedeutung |
|---|---|---|
| `DATABASE_URL` | be | JDBC-URL, z. B. `jdbc:postgresql://db:5432/myfinance` |
| `DATABASE_USER` | be, db | Datenbankbenutzer |
| `DATABASE_PASSWORD` | be, db | Passwort dazu |
| `DATABASE_NAME` | db | Name der Datenbank |
| `APP_USER` | be | Benutzername für die Basic-Authentifizierung |
| `APP_PASSWORD` | be | Passwort dazu |
| `SPRING_PROFILE` | be | Spring-Profil, auf den Stages `prod` |

`APP_USER` und `APP_PASSWORD` tauchen zusätzlich im Healthcheck des
`be`-Dienstes auf, weil `/actuator/health` hinter der Authentifizierung liegt.

### Backup-Dienst

Der `backup`-Dienst sichert die Datenbank per restic und meldet sich über
Telegram.

| Variable | Bedeutung |
|---|---|
| `ENV_POSTGRES_USER` | Datenbankbenutzer für das Backup |
| `ENV_POSTGRES_PASSWORD` | Passwort dazu |
| `ENV_POSTGRES_DATABASE` | zu sichernde Datenbank |
| `ENV_RESTIC_REPOSITORY_NAME` | Name des restic-Repositories |
| `ENV_RESTIC_PASSWORD` | Passwort des restic-Repositories |
| `ENV_TARGET_DOMAIN` | Zielhost der Sicherung |
| `ENV_TARGET_DOMAIN_USER` | Benutzer auf dem Zielhost |
| `ENV_CRON` | Zeitplan als Cron-Ausdruck |
| `ENV_PROVISION_MODE` | Betriebsmodus des Backup-Containers |
| `ENV_TELEGRAM_TOKEN` | Token des Telegram-Bots |
| `ENV_TELEGRAM_CHAT_ID` | Ziel-Chat für Meldungen |

### Lokale Entwicklung

Lokal werden diese Variablen nicht gebraucht. Das Profil `local` in
`be/src/main/resources/application-local.properties` bringt eigene Werte mit und
erwartet eine PostgreSQL-Instanz auf `localhost:5432`.
