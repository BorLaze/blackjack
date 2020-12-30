package ua.in.lbn.bj.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.in.lbn.bj.domain.Card;

import static ua.in.lbn.bj.domain.CardValue.ACE;

public class Croupier {

    public static final int WIN_SCORE = 21;

    /**
     * Make a decision on the player
     *
     * @param player player
     * @return {@link Judgment}
     */
    public Judgment judgment(Player player) {
        int score = score(player);

        if (score == WIN_SCORE) {
            return Judgment.WIN;
        }
        if (score > WIN_SCORE) {
            return Judgment.LOSS;
        }
        return Judgment.PLAY;
    }

    public Player judgment(Player playerA, Player playerB) {
        int scoreA = score(playerA);
        int scoreB = score(playerB);

        //fixme: impl EQUAL

        return (scoreA > scoreB) ? playerA : playerB;
    }

    /**
     * Calculate total score of player's cards
     *
     * @param player player's hand
     * @return score
     */
    public int score(Player player) {
        final List<List<Card>> cardPartitions = new ArrayList<>(
                player.getCardStock().stream()
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
     * Aces are treated as 11 or 1 so that the sum does not exceed {@value Croupier#WIN_SCORE}.
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

    public enum Judgment {
        /**
         * both players have equals score
         */
        EQUAL,
        /**
         * player's score > 21
         */
        LOSS,
        /**
         * player can continue game
         */
        PLAY,
        /**
         * player wants to leave game
         */
        EXIT,
        /**
         * no more cards
         */
        STAY,
        /**
         * player wins
         */
        WIN
    }

}
