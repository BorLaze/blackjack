package ua.in.lbn.bj.actor;

/**
 * Human player
 */
public class PlayerVisitor extends Player {

    public PlayerVisitor(String name) {
        super(name);
    }

    @Override
    public Decision decide(Player opponent) {
        //fixme: implements me
        return null;
    }
}
