package ua.in.lbn.bj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static ua.in.lbn.bj.CardValue.ACE;
import static ua.in.lbn.bj.Game.WIN_SCORE;

/**
 * Class describes player's hand (stock of cards)
 */
public class Hand {

    private final List<Card> cardStock = newArrayList();

    /**
     * Add new card to player's hand
     *
     * @param card card to add
     * @return this
     */
    public Hand addCard(Card card) {
        cardStock.add(card);
        return this;
    }

    /**
     * Calculate total score of player's cards
     *
     * @return score
     */
    public int score() {
        final List<List<Card>> cardPartitions = new ArrayList<>(
                cardStock.stream()
                        .collect(
                                Collectors.partitioningBy(card -> card.getCardValue() == ACE)
                        )
                        .values()
        );
        final List<Card> nonAceCards = cardPartitions.get(0);
        final List<Card> aceCards = cardPartitions.get(1);

        // special case - A+A == blackjack
        if (nonAceCards.isEmpty() && aceCards.size() == 2) {
            return WIN_SCORE;
        }

        int nonAcesScore = calcNonAcesScore(nonAceCards);
        int acesScore = calcAcesScore(aceCards, nonAcesScore);

        return nonAcesScore + acesScore;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cardStock.toArray());
    }

    /**
     * Calculate sum of non-ace cards (2-K). <br/>
     *
     * @param nonAceCards non-aces part of player's cards
     * @return score
     */
    private int calcNonAcesScore(List<Card> nonAceCards) {
        int nonAcesScore = 0;
        for (Card card : nonAceCards) {
            nonAcesScore += card.getCardValue().getIntValue(false);
        }
        return nonAcesScore;
    }

    /**
     * Calculate sum of ace cards. <br/>
     *
     * Aces are treated as 11 or 1 so that the sum does not exceed {@value ua.in.lbn.bj.Game#WIN_SCORE}.
     *
     * @param aceCards     aces part of player's cards
     * @param nonAcesScore sum of non-ace cards (2-K)
     * @return score
     */
    private int calcAcesScore(List<Card> aceCards, int nonAcesScore) {
        int acesScore = ACE.getIntValue(true) * aceCards.size();
        for (Card card : aceCards) {
            if (nonAcesScore + acesScore > WIN_SCORE) {
                acesScore -= ACE.getIntValue(true);
                acesScore += card.getCardValue().getIntValue(false);
            }
        }
        return acesScore;
    }

}
