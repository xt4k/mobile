package com.academy.mobile.model;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Character> {

    @Override
    public Character convertToDatabaseColumn(Gender gender) {
        return gender.getEng();
    }

    @Override
    public Gender convertToEntityAttribute(Character eng) {
        return Gender.valueOf(eng);
    }
}
