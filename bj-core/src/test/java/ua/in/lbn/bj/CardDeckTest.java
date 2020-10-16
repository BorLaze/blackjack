package ua.in.lbn.bj;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardDeckTest {

    private static final Logger log = LoggerFactory.getLogger(CardDeckTest.class);

    @Test
    void testNewDeck() {
        final CardDeck deck = new CardDeck();

        log.debug("Desk: {}", deck.toString());

        assertEquals("[" +
                        "A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, " +
                        "A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, " +
                        "A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, " +
                        "A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]",
                deck.toString()
        );
    }

    @Test
    void testNewShuffledDeck() {
        final CardDeck deck = new CardDeck(true);

        log.debug("Desk: {}", deck.toString());

        assertNotEquals("[" +
                        "A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, " +
                        "A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, " +
                        "A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, " +
                        "A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]",
                deck.toString()
        );
    }

    @Test
    void getNextCard() {
        CardDeck deck = new CardDeck();

        final Card card = deck.getNextCard();

        assertEquals("A♠", card.toString());
    }
}