# myfinance

Haushaltsbuch-Anwendung. Spring-Boot-Backend, Angular-Frontend, beides in
diesem Repository.

## Aufbau

```
be/            Backend (Spring Boot, Paket at.devp.myfinance)
be/Dockerfile  Backend-Image
fe/            Frontend (Angular)
fe/Dockerfile  Frontend-Image (nginx)
compose.prod.yaml / compose.test.yaml
```

Die Wurzel kennt keine Technologie mehr: sie enthaelt nur die beiden
Anwendungsteile, die Compose-Dateien und die Doku. Maven laeuft in `be/`,
pnpm in `fe/` - es gibt kein Root-Pom.

## Toolchain

| | |
|---|---|
| Java | 25 (Standard), daneben liegen 21 und 17 unter `/usr/lib/jvm/` |
| Maven | 3.9.16 |
| Node | 24 (Angular 22 verlangt `^22.22.3 \|\| ^24.15 \|\| >=26`) |
| Paketmanager | pnpm 11 |
| Spring Boot | 4.1.1 |
| Angular | 22.1 |
| PrimeNG | 22.1 |

Node 24 liegt unter `/opt/node24/bin` und steht ueber `/etc/sandbox-persistent.sh`
im PATH. Es wurde als npm-Paket `node-linux-x64` bezogen, weil `nodejs.org`,
Adoptium und sdkman von der Netzwerk-Policy gesperrt sind und `apt` nur 22.22.1
anbietet - zwei Patch-Versionen zu wenig fuer Angular 22. Die Installation lebt
nur in dieser Sandbox.

Eine andere JDK-Version waehlt man ueber `JAVA_HOME`, ohne etwas umzustellen:

```bash
cd be && JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 mvn test
```

Die JDKs 17 und 21 sind per `apt` installiert und leben nur in der Sandbox.
sdkman ist nicht verfuegbar, die Domains sind gesperrt.

## Bauen und Testen

```bash
cd be && mvn test               # Backend-Tests (aktuell 41, davon 3 @Disabled)
cd be && mvn package -DskipTests  # Jar bauen
cd fe && pnpm install           # Frontend-Abhaengigkeiten
cd fe && pnpm test              # Frontend-Tests (Vitest ueber ng test)
cd fe && pnpm run build         # Frontend-Build
```

Der Backend-Build ist gruen, wenn 41 Tests laufen, 0 fehlschlagen und 3
uebersprungen werden. Diese Zahl ist die Messlatte fuer jede groessere
Aenderung.

## Konventionen im Backend

Der Code ist nach Anwendungsfall geschnitten, nicht nach Schicht. Unter
`crud/` bekommt jede Entitaet ein eigenes Paket, darunter je ein Paket pro
Operation. Alle Pfade hier sind relativ zu
`be/src/main/java/at/devp/myfinance/`:

```
crud/transfer/TransferController.java
crud/transfer/create/TransferCreationService.java
crud/transfer/read/TransferDto.java
crud/transfer/read/TransferReadService.java
crud/transfer/edit/TransferEditService.java
crud/transfer/delete/TransferDeletionService.java
```

Daneben:

```
entity/        JPA-Entitaeten
repositories/  Spring-Data-Repositories
services/      uebergreifende Dienste
feature/       fachliche Auswertungen (financeOverview, sumOfIncome, evenize)
converter/     Entity nach DTO
config/        SecurityConfig
```

Weitere Punkte:

* Lombok wird durchgaengig verwendet (`@Data`, `@Getter`, `@RequiredArgsConstructor`).
  Es ist in der `pom.xml` ausdruecklich als `annotationProcessorPath` eingetragen,
  weil javac seit JDK 23 keine Prozessoren mehr vom Klassenpfad ausfuehrt. Ohne
  diesen Eintrag bricht der Build mit hunderten `cannot find symbol` auf allen
  Lombok-Methoden ab.
* Alle Endpunkte liegen unter `/fe/...`. Denselben Praefix leitet
  `fe/src/proxy.conf.json` im Entwicklungsbetrieb an das Backend weiter.
* Authentifizierung ist HTTP Basic mit einem einzigen In-Memory-Benutzer aus
  den Properties. Keycloak ist fuer v2.2 Titan vorgesehen, nicht vorher.
* Testdatenbank ist H2, Produktion PostgreSQL. Beide behandeln unquotierte
  Bezeichner unterschiedlich: H2 bildet auf Grossschreibung ab, PostgreSQL auf
  Kleinschreibung. Deshalb stehen in `data_test.sql` keine Anfuehrungszeichen.

## Konventionen im Frontend

Das Projekt folgt der suffixlosen Schreibweise des Angular-v20-Stilhandbuchs:
Dateien heissen `rule-overview-page.ts`, nicht `rule-overview-page.component.ts`,
und Komponentenklassen heissen `RuleOverviewPage`, nicht `RuleOverviewPageComponent`.
Der Ordner sagt, worum es sich handelt.

Serviceklassen behalten ihr Suffix (`AuthService`, `BackendService`) - so sieht es
das Stilhandbuch vor.

Beide Schreibweisen innerhalb eines Ordners zu mischen ist ausdruecklich die
schlechteste Variante. Entweder alles oder nichts.

Tests laufen ueber Vitest, eingebunden ueber Angulars eigenen `unit-test`-Builder
(`ng test`). `src/setup-tests.ts` enthaelt nur, was jsdom fehlt: Attrappen fuer
`matchMedia` und `ResizeObserver`, die PrimeNG braucht.

Komponentenbibliothek ist PrimeNG. In v2.2 Titan wird sie durch Google Material
UI ersetzt.

## Tests

In beiden Teilen des Projekts heisst das Pruefobjekt in Tests `underTest`:

```java
private IncomeCreationService underTest;
```

```ts
let underTest: RuleOverviewPageComponent;
let fixture: ComponentFixture<RuleOverviewPageComponent>;
```

Gruen heisst derzeit:

| | |
|---|---|
| Backend | 41 Tests, 3 uebersprungen |
| Frontend | 19 Testdateien, 20 Tests |

Die Frontend-Tests sind bewusst flach - die meisten pruefen nur, dass sich eine
Komponente erzeugen laesst. Das genuegt als Netz fuer Versionsspruenge, weil
genau daran Dependency Injection, Template-Kompilierung und Provider-APIs
haengen. Inhaltlich sinnvolle Tests entstehen mit dem Neubau in Titan.

## Datenbank

`spring.jpa.hibernate.ddl-auto=update` - es gibt noch kein
Schema-Migrationswerkzeug. Hibernate veraendert das Schema also beim Start
selbst, auch in Produktion. Flyway ist als Story #51 erfasst und muss vor dem
ersten Produktions-Deploy erledigt sein.

`hibernate.globally_quoted_identifiers` darf **nicht** wieder gesetzt werden.
Unter Hibernate 7 erzeugt die Einstellung `"Bank"` statt `bank`, und in
PostgreSQL sind das zwei verschiedene Tabellen - mit `ddl-auto=update` legt die
Anwendung sie dann leer neu an und liefert keine Daten mehr aus.

## Stages und Deployment

Es gibt zwei Stages, **dev** und **prod**, plus die lokale Entwicklung.

Die Pipeline baut die Images, Coolify zieht sie. Coolify baut **nicht** selbst:
sonst waeren dev und prod zwei verschiedene Artefakte und die Stage davor
verloere ihren Zweck, ausserdem laege die Build-Last auf dem Server.

Ein Deploy ist ein manuelles Redeploy in Coolify. Eine Promotion bedeutet, den
Image-Stand, der auf dev gut war, auf prod zu zeigen - ohne Neubau.

## Branches

`master` ist die Produktionslinie, `dev` der Integrationszweig fuer die
laufende v2-Arbeit. Story-Branches heissen `luna/<nummer>-<titel>`, zum
Beispiel `luna/33-migrate-spring-boot`, und zweigen von `dev` ab.

Der Tag `legacy` markiert den letzten Stand vor v2.

## Planung

Die v2-Arbeit ist in Milestones geschnitten, die Nummer gibt die Reihenfolge,
der Mondname bleibt kleben:

* **v2.1 Luna** - technischer Durchstich: lokal entwickeln, dev und prod
  deployed, heutiger Funktionsumfang
* **v2.2 Titan** - neue Backend-Architektur, Google Material UI, komplettes
  Redesign, Keycloak
* **v2.3 Europa** - neue Features

Labels: `bug`, `feature`, `chore` (im Repo: Linting, Abhaengigkeiten,
Refactoring, Aufraeumen) und `infra` (ausserhalb der Anwendung: Pipeline,
Docker, Deployment, Hosting, Monitoring).

## Netzwerk in der Sandbox

Erreichbar sind `repo.maven.apache.org`, `registry.npmjs.org`, `github.com`
und die Docker-Registry. Gesperrt sind unter anderem `docs.spring.io`,
`spring.io`, `hub.docker.com`, `repo1.maven.org` und die sdkman-Domains.

Spring-Dokumentation bekommt man trotzdem: Referenz und Wiki liegen als
Quelltext im GitHub-Repository `spring-projects/spring-boot` und sind ueber
`gh api` beziehungsweise `git clone` des Wikis erreichbar.
