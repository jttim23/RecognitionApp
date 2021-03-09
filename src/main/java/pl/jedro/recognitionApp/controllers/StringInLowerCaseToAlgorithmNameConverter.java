package pl.jedro.recognitionApp.controllers;


import org.springframework.core.convert.converter.Converter;
import pl.jedro.recognitionApp.strategies.AlgorithmNames;

public class StringInLowerCaseToAlgorithmNameConverter implements Converter<String, AlgorithmNames> {
    @Override
    public AlgorithmNames convert(String source) {
        try {
            return AlgorithmNames.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

