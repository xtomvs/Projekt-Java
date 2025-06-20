package org.example;

public class SpecialCoin extends Coin {
    private static final int SPECIAL_POINTS = 500;

    public SpecialCoin(int x, int y) {
        super(x, y);
        setImage("/images/special_coin.png");
        setValue(SPECIAL_POINTS);
    }
}
