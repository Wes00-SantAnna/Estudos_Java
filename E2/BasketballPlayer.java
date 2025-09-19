public class BasketballPlayer implements Comparable<BasketballPlayer> {
    private final String playerName;
    private final double playerHeight;

    public BasketballPlayer(String playerName, double playerHeight) {
        this.playerName = playerName;
        this.playerHeight = playerHeight;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getPlayerHeight() {
        return playerHeight;
    }

    @Override
    public int compareTo(BasketballPlayer other) {
        return Double.compare(this.playerHeight, other.playerHeight);
    }

    @Override
    public String toString() {
        return playerName + " - " + playerHeight + "m";
    }
}
