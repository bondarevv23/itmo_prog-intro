package game;

public class Tournament {
    private final Games type;
    private final Player[] players;

    private final int m;
    private final int n;
    private final int k;

    Tournament(Games type, int m, int n, int k, Player... players) {
        this.type = type;
        this.players = players;
        this.m = m;
        this.n = n;
        this.k = k;
    }

    public String[] play() {
        final int playersNumber = players.length;
        int[] results = new int[playersNumber];

        for (int i = 0; i < playersNumber; i++) {
            for (int j = i + 1; j < playersNumber; j++) {
                playGame(results, i, j);
                playGame(results, j, i);
            }
        }

        String[] table = new String[playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            table[i] = "Player " + (i + 1) + "   score: " + results[i];
        }
        return table;
    }

    private void playGame(int[] results, int x, int y) {
        final int result = new Game(
            typeBoard(type, m, n, k),
            players[x],
            players[y]
        ).play(false);
        switch(result) {
            case 1:
                results[x] += 3;
                break;
            case 2:
                results[y] += 3;
                break;
            case 0:
                results[x] += 1;
                results[y] += 1;
                break;
            default:
                throw new AssertionError("Unknown result");
        }
    }
    
    private Board typeBoard(Games type, int m, int n, int k) {
        switch(type) {
            case HEX:
                return new HexBoard(m, n, k);
            case MNK:
                return new MNKBoard(m, n, k);
            default:
                throw new AssertionError("Unknown game type");
        }
    }
}
