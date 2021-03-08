package pl.jedro.recognitionApp.strategies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
@Component
@NoArgsConstructor
public class FirstNameAlgorithmV2 implements RecognitionAlgorithm{
    @Value("${males.path}")
    private String maleTokensPath;
    @Value("${females.path}")
    private String femaleTokensPath;

    @Override
    public AlgorithmName getAlgorithmName() {
        return AlgorithmName.FirstNameAlgorithmV2;
    }
    @Override
    public Gender determineGender(List<String> names) throws IOException {
        if (firstNameMatchesToken(names.get(0),maleTokensPath)) {
            return Gender.MALE;
        } else if (firstNameMatchesToken(names.get(0),femaleTokensPath)) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }
    public boolean firstNameMatchesToken(String name, String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        boolean nameNotFound=true;
        boolean result = false;
        while ((line= bufferedReader.readLine())!=null&&nameNotFound){
                if (line.toLowerCase().equals(name.toLowerCase())){
                    nameNotFound=false;
                    result= true;
                }
        }
        return result;
    }
}
