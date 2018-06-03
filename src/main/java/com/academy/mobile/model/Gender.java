package com.academy.mobile.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum()
public enum Gender {
    @XmlEnumValue("m")MALE('m', 'м'),
    @XmlEnumValue("f")FEMALE('f', 'ж');

    private char eng;
    private char ru;

    Gender(char eng, char ru) {
        this.eng = eng;
        this.ru = ru;
    }

    public static Gender valueOf(char eng) {
        switch (eng) {
            case 'm':
                return MALE;
            case 'f':
                return FEMALE;

            default:
                throw new IllegalArgumentException(String.format(
                        "Unknown gender type '%s'", eng));
        }
    }

    public char getRu() {
        return ru;
    }

    public char getEng() {
        return eng;
    }
}
