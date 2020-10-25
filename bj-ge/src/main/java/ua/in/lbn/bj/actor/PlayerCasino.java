package ua.in.lbn.bj.actor;

/**
 * Computer player
 */
public class PlayerCasino extends Player {

    private static final int STOP_SCORE = 17;

    private final Croupier croupier;

    public PlayerCasino(String name, Croupier croupier) {
        super(name);
        this.croupier = croupier;
    }

    @Override
    public Decision decide(Player opponent) {
        int score = croupier.score(this);

        if (opponent == null) {
            if (score >= STOP_SCORE) {
                return Decision.STAY;
            }
            return Decision.HIT;
        }

        int opponentScore = croupier.score(opponent);

        if (score > opponentScore || (score == opponentScore && score >= STOP_SCORE)) {
            return Decision.STAY;
        }
        return Decision.HIT;
    }
}
