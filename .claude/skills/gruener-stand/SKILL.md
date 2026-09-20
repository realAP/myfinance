---
name: gruener-stand
description: Prueft, ob Backend, Frontend und der lokale Verbund gruen sind. Vor jedem PR und nach jedem groesseren Eingriff.
---

# Gruener Stand

Drei Messlatten. Wer eine davon reisst, ist nicht fertig.

## Backend

```bash
cd be && mvn test
```

Gruen heisst **41 Tests, 0 Fehler, 3 uebersprungen**. Die Zahl ist die
Messlatte, nicht "es kompiliert".

Eine andere JDK-Version waehlt man ueber `JAVA_HOME`, ohne etwas umzustellen:

```bash
cd be && JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 mvn test
```

## Frontend

```bash
cd fe && pnpm test
```

Gruen heisst **19 Testdateien, 20 Tests**.

Angular 22 verlangt Node `^22.22.3 || ^24.15 || >=26`. Ist nur Node 22.22.1
da, bricht `ng` mit einer Versionsmeldung ab, bevor ein einziger Test laeuft.
Node 24 liegt unter `/opt/node24/bin`. Fehlt das Verzeichnis, ist es weg -
es lebt nur in der Sandbox und wird als npm-Paket `node-linux-x64` bezogen,
weil nodejs.org gesperrt ist.

## Der Verbund

Tests sagen nichts darueber, ob die Teile zusammenspielen. Dafuer:

```bash
docker compose -f compose.local.yaml up --build -d
curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8081/
curl -s -o /dev/null -w "%{http_code}\n" -u user:password http://localhost:8081/fe/overview
curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8081/fe/overview
docker compose -f compose.local.yaml down -v
```

Erwartet: 200, 200, 401.

Der dritte Aufruf ist der wichtigste. Ein 200 ohne Anmeldung heisst, die
Authentifizierung ist offen; ein 401 **mit** Anmeldung heisst, sie ist
kaputt. Beides ist schon vorgekommen und von keinem Unit-Test bemerkt worden.
