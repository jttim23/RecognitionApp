package pl.jedro.recognitionApp.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class AlgorithmFactory {
    private Map<AlgorithmNames, RecognitionAlgorithm> algorithmsMap;

    @Autowired
    public AlgorithmFactory(Set<RecognitionAlgorithm> algorithmsSet) {

        createAlgorithm(algorithmsSet);
    }

    private void createAlgorithm(Set<RecognitionAlgorithm> algorithmSet) {
        algorithmsMap = new HashMap<>();
        algorithmSet.forEach(
                algorithm -> algorithmsMap.put(algorithm.getAlgorithmName(), algorithm));
    }

    public RecognitionAlgorithm findAlgorithm(AlgorithmNames algorithmName) {
        return algorithmsMap.get(algorithmName);
    }


}
