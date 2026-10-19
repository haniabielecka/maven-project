# order-management

Aplikacja w Javie do obsługi zamówień: wczytuje pliki JSON z żądaniami złożenia zamówienia z katalogu i generuje dla każdego numer zamówienia. Projekt na zajęcia z Narzędzi programistycznych (PWr).

## Wymagania

- Java 25
- Maven
- Docker (opcjonalnie)

## Konfiguracja

Katalog z zamówieniami jest ustawiony w `src/main/resources/appsettings.properties`:

```properties
orders.directory=orders
```

W Dockerze używany jest plik `appsettings.docker.properties` (ścieżka kontenera `/app/orders`).

## Budowanie i uruchomienie lokalne

```bash
mvn package
java -jar target/order-management-1.0-SNAPSHOT.jar
```

Podczas budowania Maven uruchamia też Checkstyle (faza `validate`) i generuje Javadoc (faza `compile`, wynik w `target/reports/apidocs`).

## Docker

Zbudowanie obrazu (w katalogu głównym projektu):

```bash
docker build -t order-management .
```

Uruchomienie przez Docker Compose (pliki JSON podpięte jako volume):

```bash
cd order-compose
docker compose up
```

Katalog `order-compose/orders` zawiera przykładowe zamówienia (`order1.json`, `order2.json`).
