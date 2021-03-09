package pl.jedro.recognitionApp.controllers;

import org.springframework.core.convert.converter.Converter;
import pl.jedro.recognitionApp.model.Genders;

public class StringInLowerCaseToGenderConverter implements Converter<String, Genders> {
    @Override
    public Genders convert(String source) {

            return Genders.valueOf(source.toUpperCase());

        }
    }
