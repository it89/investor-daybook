package com.github.it89.investordaybook.model.daybook;

public enum SecurityType {
    STOCK(false),
    BOND(true),
    GDR(false),
    SHARE(false);

    private boolean isBond;

    private SecurityType(boolean isBond) {
        this.isBond = isBond;
    }

    public boolean isBond() {
        return isBond;
    }
}
