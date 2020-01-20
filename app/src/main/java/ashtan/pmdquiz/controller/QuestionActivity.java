package ashtan.pmdquiz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ashtan.pmdquiz.R;
import ashtan.pmdquiz.model.Nature;
import ashtan.pmdquiz.model.Question;

public class QuestionActivity extends AppCompatActivity {

    private Question currQ;

    private TextView qText;
    private Button opt1;
    private Button opt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get question data
        currQ = MainActivity.questions[MainActivity.selectedQs[MainActivity.currQNum]];

        //set display
        qText = (TextView) findViewById(R.id.q_text);
        String displayQText = MainActivity.currQNum + 1 + ". " + currQ.getText();   //+1 to acct for 0-indexing
        qText.setText(displayQText);

        opt1 = (Button) findViewById(R.id.opt1);
        opt1.setText(currQ.getChoices().first);

        opt2 = (Button) findViewById(R.id.opt2);
        opt2.setText(currQ.getChoices().second);
    }




    //Event Handlers
    public void select1(View v) {
        updateQuizResults(0);

        MainActivity.currQNum++;
        nextScreen();
    }

    public void select2(View v) {
        updateQuizResults(1);

        MainActivity.currQNum++;
        nextScreen();
    }




    //Helpers
    private void nextScreen() {
        if (MainActivity.currQNum >= MainActivity.selectedQs.length) {
            startActivity(new Intent(QuestionActivity.this, ResultsActivity.class));
        } else {
            startActivity(new Intent(QuestionActivity.this, QuestionActivity.class));
        }
    }

    private void updateQuizResults(int leftOrRight) {   //0 = left, 1 = right
        List<Pair<Nature,Integer>> resultsToAdd;
        if (leftOrRight == 0) {
            resultsToAdd = currQ.getResults().first;
        } else {
            resultsToAdd = currQ.getResults().second;
        }

        //add nature points to cumulative results
        for (Pair<Nature, Integer> p : resultsToAdd) {
            MainActivity.currQuizResults.put(
                    p.first, MainActivity.currQuizResults.get(p.first) + p.second);
        }
    }
}
