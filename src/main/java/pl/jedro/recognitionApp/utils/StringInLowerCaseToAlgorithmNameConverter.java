package pl.jedro.recognitionApp.utils;


import org.springframework.core.convert.converter.Converter;
import pl.jedro.recognitionApp.strategies.AlgorithmNames;

public class StringInLowerCaseToAlgorithmNameConverter implements Converter<String, AlgorithmNames> {
   //Makes possible to pass enum parameters also in lower case
    @Override
    public AlgorithmNames convert(String source) {
            return AlgorithmNames.valueOf(source.toUpperCase());

    }
}

