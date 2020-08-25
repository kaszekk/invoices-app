package pl.lukasz.model;

public enum Vat {
    RATE_0(0f),
    RATE_5(0.05f),
    RATE_8(0.08f),
    RATE_23(0.23f);

    private final float rate;

    Vat(float rate) {
        this.rate = rate;
    }

    public float getValue() {
        return rate;
    }
}
