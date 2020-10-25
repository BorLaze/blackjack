package ua.in.lbn.bj.actor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.in.lbn.bj.domain.CardDeck;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DealerTest {

    private static final Logger log = LoggerFactory.getLogger(DealerTest.class);

    @Test
    void deal() {
        Croupier croupier = new Croupier();
        Dealer dealer = new Dealer(
                new CardDeck(true),
                croupier,
                new PlayerCasino("playerA", croupier),
                new PlayerCasino("playerB", croupier)
        );

        Player player = dealer.deal();

        log.debug("Winner: {}, score {}", player, croupier.score(player));

        assertNotNull(player);
    }
}