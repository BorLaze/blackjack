package ua.in.lbn.bj.domain;

import lombok.Data;

@Data
public class Card {

    private final CardSuit cardSuit;
    private final CardValue cardValue;

    @Override
    public String toString() {
        return getCardValue().value + getCardSuit().sign;
    }
}
