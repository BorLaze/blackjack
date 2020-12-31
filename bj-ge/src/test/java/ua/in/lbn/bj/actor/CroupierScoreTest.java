package ua.in.lbn.bj.actor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.in.lbn.bj.domain.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.in.lbn.bj.domain.CardSuit.DIAMONDS;
import static ua.in.lbn.bj.domain.CardSuit.HEARTS;
import static ua.in.lbn.bj.domain.CardSuit.SPADES;
import static ua.in.lbn.bj.domain.CardValue.ACE;
import static ua.in.lbn.bj.domain.CardValue.EIGHT;
import static ua.in.lbn.bj.domain.CardValue.FIVE;
import static ua.in.lbn.bj.domain.CardValue.FOUR;
import static ua.in.lbn.bj.domain.CardValue.KING;
import static ua.in.lbn.bj.domain.CardValue.NINE;
import static ua.in.lbn.bj.domain.CardValue.QUEEN;
import static ua.in.lbn.bj.domain.CardValue.SEVEN;
import static ua.in.lbn.bj.domain.CardValue.THREE;
import static ua.in.lbn.bj.domain.CardValue.TWO;

class CroupierScoreTest {

    private static final Logger log = LoggerFactory.getLogger(CroupierScoreTest.class);

    private static final String name = "CroupierScoreTest";

    private final Croupier croupier = new Croupier();

    @Test
    void score_A() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(11, score);
    }

    @Test
    void score_A2A() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, TWO))
                .hit(new Card(DIAMONDS, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(14, score);
    }

    @Test
    void score_AA() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_K() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(10, score);
    }

    @Test
    void score_KA() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_AK() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, KING));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_234() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, TWO))
                .hit(new Card(SPADES, THREE))
                .hit(new Card(SPADES, FOUR));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(9, score);
    }


    @Test
    void score_K2Q() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, TWO))
                .hit(new Card(SPADES, QUEEN));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(22, score);
    }


    @Test
    void score_KAQ() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, QUEEN));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_A73() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, SEVEN))
                .hit(new Card(SPADES, THREE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_A5A3A() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, FIVE))
                .hit(new Card(DIAMONDS, ACE))
                .hit(new Card(SPADES, THREE))
                .hit(new Card(HEARTS, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_AAK() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE))
                .hit(new Card(SPADES, KING));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(12, score);
    }

    @Test
    void score_778() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, SEVEN))
                .hit(new Card(DIAMONDS, SEVEN))
                .hit(new Card(SPADES, EIGHT));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(22, score);
    }

    @Test
    void score_K9() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(19, score);
    }

    @Test
    void score_K9A() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(SPADES, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(20, score);
    }

    @Test
    void score_K9AA() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }

    @Test
    void score_K9AAA() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE))
                .hit(new Card(HEARTS, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(22, score);
    }

    @Test
    void score_A9A() {
        AbstractPlayer player = new Casino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(DIAMONDS, ACE));

        final int score = croupier.score(player);
        log.debug("score: {}, {}", score, player);

        assertEquals(21, score);
    }
}