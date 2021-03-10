package pl.jedro.recognitionApp.utils;

import org.springframework.core.convert.converter.Converter;
import pl.jedro.recognitionApp.model.Genders;

public class StringInLowerCaseToGenderConverter implements Converter<String, Genders> {
    //Makes possible to pass enum parameters also in lower case
    @Override
    public Genders convert(String source) {

        return Genders.valueOf(source.toUpperCase());

    }
}
