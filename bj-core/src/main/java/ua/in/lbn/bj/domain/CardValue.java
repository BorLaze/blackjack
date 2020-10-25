package ua.in.lbn.bj.domain;

import org.apache.commons.lang3.math.NumberUtils;

public enum CardValue {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    public final String value;

    CardValue(String value) {
        this.value = value;
    }

    public int getIntValue(boolean aceAs11) {
        switch (this) {
            case ACE:
                return aceAs11 ? 11 : 1;
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                return NumberUtils.toInt(this.value);
        }
    }
}
