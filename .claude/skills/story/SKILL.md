---
name: story
description: Legt einen Story-Branch an und oeffnet den PR. Nutzen, wenn an einer GitHub-Story gearbeitet wird.
---

# Story bearbeiten

## Branch

Story-Branches heissen `luna/<nummer>-<titel>` und zweigen von `dev` ab.
`master` ist die Produktionslinie, `dev` der Integrationszweig.

```bash
git checkout dev
git checkout -b luna/51-flyway
```

Die Nummer im Branchnamen ist nicht Kosmetik: `close-story-on-merge.yml`
liest sie aus und schliesst die Story beim Merge.

## Aufeinander aufbauende PRs

Haengen mehrere Stories voneinander ab, zweigt jeder Branch vom **vorigen**
ab und der PR bekommt den vorigen als Base:

```bash
gh pr create --base luna/35-repo-struktur --head luna/51-flyway
```

So zeigt jeder PR nur seinen eigenen Diff. GitHub haengt die Basis
automatisch um, sobald der darunterliegende gemerged ist. Gelesen und
gemerged wird von unten nach oben.

## Was in den PR-Text gehoert

Nicht was geaendert wurde - das steht im Diff. Sondern:

* **warum** es ein Problem war, mit Dateiname und Zeile
* was dabei **aufgefallen** ist, auch wenn es nicht zur Story gehoert
* **wie geprueft** wurde, mit den Zahlen aus dem Lauf

Ein PR ohne Pruefnachweis ist eine Behauptung.

## Im Sandbox: HTTPS statt SSH

`origin` zeigt auf `git@github.com:`, im Sandbox gibt es aber keinen
SSH-Schluessel. Der Proxy injiziert Zugangsdaten nur fuer HTTPS. Statt die
Repo-Konfiguration zu aendern, pro Aufruf umschreiben:

```bash
git -c url."https://github.com/".insteadOf="git@github.com:" push -u origin <branch>
```
