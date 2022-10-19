package com.dmdev.entity.presets;

public enum Religion {
    ORTHODOX("Православие"),
    CATHOLIC("Католицизм"),
    BUDDHISM("Буддизм"),
    ISLAM("Ислам"),
    ATHEISM("Атеизм"),
    PAGAN("Язычество");

    private final String religion;

    Religion(String religion) {
        this.religion = religion;
    }
}
