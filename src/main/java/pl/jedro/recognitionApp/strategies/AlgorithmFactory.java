package pl.jedro.recognitionApp.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class AlgorithmFactory {
    private Map<AlgorithmName, RecognitionAlgorithm> algorithms;

    @Autowired
    public AlgorithmFactory(Set<RecognitionAlgorithm> algorithmSet) {
        createStrategy(algorithmSet);
    }

    public RecognitionAlgorithm findAlgorithm(AlgorithmName algorithmName) {
        return algorithms.get(algorithmName);
    }
    private void createStrategy(Set<RecognitionAlgorithm> algorithmSet) {
        algorithms = new HashMap<AlgorithmName, RecognitionAlgorithm>();
        algorithmSet.forEach(
                algorithm ->algorithms.put(algorithm.getAlgorithmName(), algorithm));
    }
}
