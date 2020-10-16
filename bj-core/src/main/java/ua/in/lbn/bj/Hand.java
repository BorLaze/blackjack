package ua.in.lbn.bj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static ua.in.lbn.bj.CardValue.ACE;
import static ua.in.lbn.bj.Game.WIN_SCORE;

public class Hand {

    private final List<Card> cardStock = newArrayList();

    public Hand addCard(Card card) {
        cardStock.add(card);
        return this;
    }

    public int score() {
        final List<List<Card>> cards = new ArrayList<>(
                cardStock.stream()
                        .collect(
                                Collectors.partitioningBy(card -> card.getCardValue() == ACE)
                        )
                        .values()
        );

        final List<Card> nonAceCards = cards.get(0);
        final List<Card> aceCards = cards.get(1);

        if (nonAceCards.isEmpty() && aceCards.size() == 2) {
            return WIN_SCORE;
        }

        int score = 0;
        for (Card card : nonAceCards) {
            score += card.getCardValue().getIntValue(false);
        }
        int acesScore = ACE.getIntValue(true) * aceCards.size();
        for (Card card : aceCards) {
            if (score + acesScore > WIN_SCORE) {
                acesScore -= ACE.getIntValue(true);
                acesScore += card.getCardValue().getIntValue(false);
            }
        }

        return score + acesScore;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cardStock.toArray());
    }

}
