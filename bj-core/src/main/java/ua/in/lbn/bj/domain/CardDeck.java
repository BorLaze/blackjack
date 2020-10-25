package ua.in.lbn.bj.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newLinkedList;

public class CardDeck {

    private final List<Card> cardList = newLinkedList();

    public CardDeck() {
        this(false);
    }

    public CardDeck(boolean shuffled) {
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardValue cardValue : CardValue.values()) {
                cardList.add(new Card(cardSuit, cardValue));
            }
        }
        if (shuffled) {
            Collections.shuffle(this.cardList);
        }
    }

    public Card getNextCard() {
        if (cardList.isEmpty()) {
            throw new IllegalStateException("empty deck");
        }

        return cardList.remove(0);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cardList.toArray());
    }
}
