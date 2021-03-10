package pl.jedro.recognitionApp.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.jedro.recognitionApp.utils.StringInLowerCaseToAlgorithmNameConverter;
import pl.jedro.recognitionApp.utils.StringInLowerCaseToGenderConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringInLowerCaseToGenderConverter());
        registry.addConverter(new StringInLowerCaseToAlgorithmNameConverter());
    }
}