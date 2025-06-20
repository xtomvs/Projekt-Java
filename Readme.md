# 🕹️ My2DGame – Java 2D Tile Game

Prosta gra 2D stworzona w Javie z wykorzystaniem Swinga. Gracz porusza się po planszy, zbiera monety, unika pułapek i przeciwników, a jego wynik zapisywany jest jako rekord. Gra kończy się po upływie czasu.

---

## 🎮 Funkcje

- ✅ Poruszanie się gracza po siatce 20x10 pól (każde 50x50 pikseli)
- ✅ Monety standardowe i specjalne (dające więcej punktów)
- ✅ Przeszkody blokujące ruch
- ✅ Pułapki, które odejmują punkty
- ✅ Przeciwnik poruszający się losowo – dotknięcie kończy grę
- ✅ Czas gry 30 sekund + bonusy za monety
- ✅ Ekran końcowy z wynikiem i rekordem (high score)
- ✅ Dźwięki (muzyka, pułapki, monety, przeciwnik)
- ✅ System restartu gry przez wciśnięcie `R`

---

## 🛠️ Wymagania

- Java 17 lub nowsza (zalecane: OpenJDK 21+)
- IntelliJ IDEA lub inny IDE z obsługą Maven/Gradle
- Obsługa `resources/` (obrazki i dźwięki w `src/main/resources/images/` i `src/main/resources/sounds/`)

---

## ▶️ Uruchomienie

1. Sklonuj repozytorium:

   ```bash
   git clone https://github.com/twoje-konto/My2DGame.git
   cd My2DGame
   ```

2. Otwórz w IntelliJ IDEA jako projekt Maven (jeśli używasz Maven).

3. Uruchom klasę `Main.java`.

---

## ⌨️ Sterowanie

| Klawisz | Akcja              |
|--------:|--------------------|
| W / ↑    | Ruch w górę         |
| A / ←    | Ruch w lewo        |
| S / ↓    | Ruch w dół         |
| D / →    | Ruch w prawo       |
| R       | Restart po zakończeniu gry |

---

## 📁 Struktura katalogów

```
My2DGame/
│
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── Main.java
│   │   │   ├── Board.java
│   │   │   ├── Player.java
│   │   │   ├── Coin.java
│   │   │   ├── SpecialCoin.java
│   │   │   ├── Trap.java
│   │   │   ├── Enemy.java
│   │   │   ├── Obstacle.java
│   │   │   └── Entity.java
│   │   └── resources/
│   │       ├── images/
│   │       │   ├── player.png
│   │       │   ├── coin.png
│   │       │   ├── special_coin.png
│   │       │   ├── obstacle.png
│   │       │   ├── trap.png
│   │       │   ├── enemy.png
│   │       │   └── background.png
│   │       └── sounds/
│   │           ├── music.wav
│   │           ├── coin.wav
│   │           ├── trap.wav
│   │           └── enemy.wav
```

---

## 📸 Zrzuty ekranu

> *(Dodaj zrzuty ekranu tutaj, np. gameplay, ekran końcowy)*

---

## 📄 Licencja

Projekt edukacyjny — do swobodnego użytku i modyfikacji. Nie zawiera zewnętrznych zależności.

---

## ✍️ Autor

Projekt stworzony przez [Twoje Imię / GitHub].