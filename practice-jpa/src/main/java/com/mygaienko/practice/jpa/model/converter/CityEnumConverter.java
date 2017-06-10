package com.mygaienko.practice.jpa.model.converter;

import com.mygaienko.practice.jpa.model.CityEnum;
import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by enda1n on 10.06.2017.
 */
@Converter
public class CityEnumConverter implements AttributeConverter<CityEnum, String> {

    public static final String UNDERSCORE = "_";
    public static final String WHITE_SPACE = " ";

    @Override
    public String convertToDatabaseColumn(CityEnum attribute) {
        if (attribute == null) {
            return StringUtils.EMPTY;
        } else {
            return attribute.name().replace(UNDERSCORE, WHITE_SPACE);
        }
    }

    @Override
    public CityEnum convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return null;
        } else {
            return CityEnum.valueOf(dbData.replace(WHITE_SPACE, UNDERSCORE));
        }
    }
}
