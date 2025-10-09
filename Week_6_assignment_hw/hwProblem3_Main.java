class Game {
    String name;
    int players;

    // Constructor
    public Game(String name, int players) {
        this.name = name;
        this.players = players;
    }

    // Override toString
    @Override
    public String toString() {
        return "Game{name='" + name + "', players=" + players + "}";
    }

    // Override equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object
        if (obj == null || getClass() != obj.getClass()) return false;

        Game game = (Game) obj;
        return players == game.players && name.equals(game.name);
    }

    // Override hashCode (good practice with equals)
    @Override
    public int hashCode() {
        return name.hashCode() + players;
    }
}

class CardGame extends Game {
    String cardType;

    // Constructor
    public CardGame(String name, int players, String cardType) {
        super(name, players);
        this.cardType = cardType;
    }

    // Override toString
    @Override
    public String toString() {
        return super.toString() + ", CardGame{cardType='" + cardType + "'}";
    }

    // Override equals
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (this.getClass() != obj.getClass()) return false;

        CardGame cardGame = (CardGame) obj;
        return cardType.equals(cardGame.cardType);
    }

    // Override hashCode
    @Override
    public int hashCode() {
        return super.hashCode() + cardType.hashCode();
    }
}

public class hwProblem3_Main {
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        Game g3 = new Game("Football", 22);

        CardGame cg1 = new CardGame("Poker", 4, "Standard");
        CardGame cg2 = new CardGame("Poker", 4, "Standard");
        CardGame cg3 = new CardGame("Poker", 4, "Special");

        // Testing toString()
        System.out.println(g1);
        System.out.println(cg1);

        // Testing equals()
        System.out.println("g1 equals g2? " + g1.equals(g2)); // true
        System.out.println("g1 equals g3? " + g1.equals(g3)); // false
        System.out.println("cg1 equals cg2? " + cg1.equals(cg2)); // true
        System.out.println("cg1 equals cg3? " + cg1.equals(cg3)); // false
    }
}
