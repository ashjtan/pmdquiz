package ashtan.pmdquiz.model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String text;
    private Pair<String, String> choices;
    private Pair<List<Pair<Nature, Integer>>, List<Pair<Nature, Integer>>>  results;

    public Question(String text, String opt1, String opt2,
                    String result1, String result2) {
      this.text = text;
      this.choices = new Pair<>(opt1, opt2);
      this.results = new Pair<>(parseResults(result1), parseResults(result2));

    }

    private List<Pair<Nature, Integer>> parseResults(String result) {
        List<Pair<Nature, Integer>> results = new ArrayList<>();

        for (String s : result.split(",")) {
            String[] curr = s.trim().split(" ");

            results.add(new Pair<>(
                    Nature.valueOf(curr[0].toUpperCase()), Integer.parseInt(curr[1])));
        }

        return results;
    }

    public String getText() {
        return text;
    }

    public Pair<String, String> getChoices() {
        return choices;
    }

    public Pair<List<Pair<Nature, Integer>>, List<Pair<Nature, Integer>>> getResults() {
        return results;
    }



}
