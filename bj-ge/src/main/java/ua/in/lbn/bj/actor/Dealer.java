package ua.in.lbn.bj.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.in.lbn.bj.domain.CardDeck;

public class Dealer {

    private static final Logger log = LoggerFactory.getLogger(Dealer.class);

    private final CardDeck cardDeck;

    private final Croupier croupier;
    private final AbstractPlayer playerA;
    private final AbstractPlayer playerB;

    public Dealer(CardDeck cardDeck, Croupier croupier, AbstractPlayer playerA, AbstractPlayer playerB) {
        this.cardDeck = cardDeck;
        this.croupier = croupier;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public AbstractPlayer deal() {
        Croupier.Judgment judgmentA = deal(playerA);
        switch (judgmentA) {
            case PLAY:
                throw new IllegalStateException(String.format("Illegal state %s for player %s", judgmentA, playerA));
            case LOSS:
                return playerB;
            case EXIT:
                return null;
            case STAY:
                Croupier.Judgment judgmentB = deal(playerB, playerA);
                switch (judgmentB) {
                    case PLAY:
                        throw new IllegalStateException(String.format("Illegal state %s for player %s", judgmentB, playerB));
                    case LOSS:
                        return playerA;
                    case EXIT:
                        return null;
                    case STAY:
                        return croupier.judgment(playerA, playerB);
                    case WIN:
                        return playerB;
                    default:
                        throw new IllegalStateException("Unexpected judgmentB: " + judgmentB);
                }
            case WIN:
                return playerA;
            default:
                throw new IllegalStateException("Unexpected judgmentA: " + judgmentA);
        }
    }

    private Croupier.Judgment deal(AbstractPlayer player) {
        return deal(player, null);
    }

    private Croupier.Judgment deal(AbstractPlayer player, AbstractPlayer opponent) {
        while (true) {
            Croupier.Judgment judgment = croupier.judgment(player);
            switch (judgment) {
                case PLAY:
                    switch (player.decide(opponent)) {
                        case HIT:
                            player.hit(cardDeck.getNextCard());
                            break;
                        case EXIT:
                            judgment = Croupier.Judgment.EXIT;
                            break;
                        case STAY:
                            judgment = Croupier.Judgment.STAY;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + player.decide(opponent));
                    }
                    break;
                case LOSS:
                case WIN:
                    break;
                default:
                    throw new IllegalStateException("Unexpected judgment: " + judgment);
            }

            log.debug("{} ({}), {}", judgment, croupier.score(player), player);
            if (judgment != Croupier.Judgment.PLAY) {
                return judgment;
            }
        }
    }
}
