package ua.in.lbn.bj.actor;

import java.util.Arrays;
import java.util.List;

import ua.in.lbn.bj.domain.Card;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Abstract player
 */
public abstract class Player {

    private final List<Card> cardStock = newArrayList();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    /**
     * Add new card to player's hand
     *
     * @param card card to add
     * @return this
     */
    public Player hit(Card card) {
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
     * @return {@link Player.Decision}
     */
    public abstract Decision decide(Player opponent);

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, Arrays.deepToString(cardStock.toArray()));
    }

    public enum Decision {
        HIT,
        STAY,
        EXIT
    }
}
