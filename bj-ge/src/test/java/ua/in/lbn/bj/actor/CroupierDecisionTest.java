package ua.in.lbn.bj.actor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.in.lbn.bj.domain.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.in.lbn.bj.actor.Croupier.Judgment.PLAY;
import static ua.in.lbn.bj.actor.Croupier.Judgment.LOSS;
import static ua.in.lbn.bj.actor.Croupier.Judgment.WIN;
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

class CroupierDecisionTest {

    private static final Logger log = LoggerFactory.getLogger(CroupierDecisionTest.class);

    private static final String name = "CroupierDecisionTest";

    private final Croupier croupier = new Croupier();

    @Test
    void decide_A() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }

    @Test
    void decide_A2A() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, TWO))
                .hit(new Card(DIAMONDS, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }

    @Test
    void decide_AA() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_K() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }

    @Test
    void decide_KA() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_AK() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, KING));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_234() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, TWO))
                .hit(new Card(SPADES, THREE))
                .hit(new Card(SPADES, FOUR));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }


    @Test
    void decide_K2Q() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, TWO))
                .hit(new Card(SPADES, QUEEN));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(LOSS, decision);
    }


    @Test
    void decide_KAQ() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, QUEEN));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_A73() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, SEVEN))
                .hit(new Card(SPADES, THREE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_A5A3A() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, FIVE))
                .hit(new Card(DIAMONDS, ACE))
                .hit(new Card(SPADES, THREE))
                .hit(new Card(HEARTS, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_AAK() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE))
                .hit(new Card(SPADES, KING));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }

    @Test
    void decide_778() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, SEVEN))
                .hit(new Card(DIAMONDS, SEVEN))
                .hit(new Card(SPADES, EIGHT));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(LOSS, decision);
    }

    @Test
    void decide_K9() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }

    @Test
    void decide_K9A() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(SPADES, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(PLAY, decision);
    }

    @Test
    void decide_K9AA() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }

    @Test
    void decide_K9AAA() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, KING))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(SPADES, ACE))
                .hit(new Card(DIAMONDS, ACE))
                .hit(new Card(HEARTS, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(LOSS, decision);
    }

    @Test
    void decide_A9A() {
        Player player = new PlayerCasino(name, croupier);
        player.hit(new Card(SPADES, ACE))
                .hit(new Card(SPADES, NINE))
                .hit(new Card(DIAMONDS, ACE));

        final Croupier.Judgment decision = croupier.judgment(player);
        log.debug("decision: {}, {}", decision, player);

        assertEquals(WIN, decision);
    }
}