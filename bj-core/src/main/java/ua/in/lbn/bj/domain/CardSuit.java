package ua.in.lbn.bj.domain;

public enum CardSuit {
    SPADES("♠"),
    CLUBS("♣"),
    DIAMONDS("♦"),
    HEARTS("♥");

    public final String sign;

    CardSuit(String sign) {
        this.sign = sign;
    }
}
