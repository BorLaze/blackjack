package ua.in.lbn.bj.actor;

import java.util.Arrays;
import java.util.List;

import ua.in.lbn.bj.domain.Card;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Abstract player
 */
public abstract class AbstractPlayer {

    private final List<Card> cardStock = newArrayList();
    private final String name;

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    /**
     * Add new card to player's hand
     *
     * @param card card to add
     * @return this
     */
    public AbstractPlayer hit(Card card) {
        cardStock.add(card);
        return this;
    }

    public List<Card> getCardStock() {
        return newArrayList(cardStock);
    }

    /**
     * Decide next step
     *
     * @param opponent opponent player
     * @return {@link AbstractPlayer.Decision}
     */
    public abstract Decision decide(AbstractPlayer opponent);

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, Arrays.deepToString(cardStock.toArray()));
    }

    public enum Decision {
        EXIT,
        HIT,
        STAY,
    }
}
