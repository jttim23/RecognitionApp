package pl.jedro.recognitionApp.controllers;

import org.springframework.core.convert.converter.Converter;
import pl.jedro.recognitionApp.model.Genders;

public class StringToGenderConverter implements Converter<String, Genders> {
    @Override
    public Genders convert(String source) {
        try {
            return Genders.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}