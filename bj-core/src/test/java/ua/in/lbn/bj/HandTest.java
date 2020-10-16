package ua.in.lbn.bj;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.in.lbn.bj.CardSuit.SPADES;
import static ua.in.lbn.bj.CardValue.ACE;
import static ua.in.lbn.bj.CardValue.EIGHT;
import static ua.in.lbn.bj.CardValue.FIVE;
import static ua.in.lbn.bj.CardValue.FOUR;
import static ua.in.lbn.bj.CardValue.KING;
import static ua.in.lbn.bj.CardValue.NINE;
import static ua.in.lbn.bj.CardValue.QUEEN;
import static ua.in.lbn.bj.CardValue.SEVEN;
import static ua.in.lbn.bj.CardValue.THREE;
import static ua.in.lbn.bj.CardValue.TWO;


class HandTest {

    private static final Logger log = LoggerFactory.getLogger(HandTest.class);

    @Test
    void score_A() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(11, score);
    }

    @Test
    void score_AA() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_K() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(10, score);
    }

    @Test
    void score_KA() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_AK() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, KING));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_234() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, TWO))
                .addCard(new Card(SPADES, THREE))
                .addCard(new Card(SPADES, FOUR));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(9, score);
    }


    @Test
    void score_K2Q() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, TWO))
                .addCard(new Card(SPADES, QUEEN));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(22, score);
    }


    @Test
    void score_KAQ() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, QUEEN));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_A73() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, SEVEN))
                .addCard(new Card(SPADES, THREE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_A5A3A() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, FIVE))
                .addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, THREE))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_AAK() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, KING));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(12, score);
    }

    @Test
    void score_778() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, SEVEN))
                .addCard(new Card(SPADES, SEVEN))
                .addCard(new Card(SPADES, EIGHT));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(22, score);
    }

    @Test
    void score_K9() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, NINE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(19, score);
    }

    @Test
    void score_K9A() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, NINE))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(20, score);
    }

    @Test
    void score_K9AA() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, NINE))
                .addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }

    @Test
    void score_K9AAA() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, KING))
                .addCard(new Card(SPADES, NINE))
                .addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(22, score);
    }

    @Test
    void score_A9A() {
        Hand hand = new Hand();
        hand.addCard(new Card(SPADES, ACE))
                .addCard(new Card(SPADES, NINE))
                .addCard(new Card(SPADES, ACE));

        final int score = hand.score();
        log.debug("score: {}, hand: {}", score, hand);

        assertEquals(21, score);
    }
}