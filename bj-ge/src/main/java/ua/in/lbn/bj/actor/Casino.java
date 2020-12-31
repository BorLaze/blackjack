package ua.in.lbn.bj.actor;

/**
 * Computer player
 */
public class Casino extends AbstractPlayer {

    private static final int STOP_SCORE = 17;

    private final Croupier croupier;

    public Casino(String name, Croupier croupier) {
        super(name);
        this.croupier = croupier;
    }

    @Override
    public Decision decide(AbstractPlayer opponent) {
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
