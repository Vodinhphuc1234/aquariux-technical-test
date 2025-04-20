package com.aquariux.test.cryptocurrencytradingsystem.commons.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Symbol {
    ETHUSDT (1),
    BTCUSDT (2);

    private final int value;

    private static final Set<String> supportedSymbols = new HashSet<>();

    public static boolean isSupported(String symbol) {
        return supportedSymbols.contains(symbol.toUpperCase());
    }

    static {
        supportedSymbols.addAll(Arrays.stream(Symbol.values()).map(Enum::name).toList());
    }

    Symbol(final int i) {
        this.value = i;
    }

    public int getStatus() { return value; }

}
