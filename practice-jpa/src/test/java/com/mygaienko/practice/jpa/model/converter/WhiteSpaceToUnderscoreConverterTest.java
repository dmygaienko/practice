package com.mygaienko.practice.jpa.model.converter;

import com.mygaienko.practice.jpa.model.CityEnum;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by enda1n on 10.06.2017.
 */
public class WhiteSpaceToUnderscoreConverterTest {

    @Test
    public void testConvertToDatabaseColumnCITY_ENUM4_() throws Exception {
        CityEnumConverter converter = new CityEnumConverter();
        String converted = converter.convertToDatabaseColumn(CityEnum.CITY_ENUM4_);

        assertThat(converted, is("CITY ENUM4 "));
    }

    @Test
    public void testConvertToDatabaseColumnCITY_ENUM1() throws Exception {
        CityEnumConverter converter = new CityEnumConverter();
        String converted = converter.convertToDatabaseColumn(CityEnum.CITY_ENUM1);

        assertThat(converted, is("CITY ENUM1"));
    }

    @Test
    public void testConvertToEntityAttributeCITY_ENUM2() throws Exception {
        CityEnumConverter converter = new CityEnumConverter();
        CityEnum enumConverted = converter.convertToEntityAttribute("CITY ENUM2");

        assertThat(enumConverted, is(CityEnum.CITY_ENUM2));
    }
}