package Utils;

import java.util.*;

public class ResultsStorage {
    public static List<byte[]> screenShotsStorage;
    static {
        screenShotsStorage = new ArrayList<>();
    }
    public static List<ScenarioResult> resultStorage;
    static {
        resultStorage = new ArrayList<>();
    }

    public static void addResult(String feature, String scenario, String result) {
        resultStorage.add(new ScenarioResult(feature, scenario, result));
    }
}

class ScenarioResult {
    public String feature;
    public String scenario;
    public String result;

    public ScenarioResult(String featureName, String scenarioName, String scenarioResult) {
        feature = featureName.replace('-',' ');
        scenario = scenarioName;
        result = scenarioResult;
    }
}