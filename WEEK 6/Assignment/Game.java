class Game {
    protected String name;
    protected int players;

    public Game(String name, int players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game{name='" + name + "', players=" + players + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Game game = (Game) obj;
        return players == game.players && name.equals(game.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + players;
    }
}

class CardGame extends Game {
    private String deckType;

    public CardGame(String name, int players, String deckType) {
        super(name, players);
        this.deckType = deckType;
    }

    @Override
    public String toString() {
        return super.toString() + ", CardGame{deckType='" + deckType + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        CardGame cg = (CardGame) obj;
        return deckType.equals(cg.deckType);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + deckType.hashCode();
    }
}

public class GameTest {
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        CardGame cg1 = new CardGame("Poker", 4, "Standard");
        CardGame cg2 = new CardGame("Poker", 4, "Standard");
        CardGame cg3 = new CardGame("Poker", 4, "Uno");

        System.out.println(g1);
        System.out.println(cg1);

        System.out.println("g1 equals g2: " + g1.equals(g2)); // true
        System.out.println("cg1 equals cg2: " + cg1.equals(cg2)); // true
        System.out.println("cg1 equals cg3: " + cg1.equals(cg3)); // false
    }
}