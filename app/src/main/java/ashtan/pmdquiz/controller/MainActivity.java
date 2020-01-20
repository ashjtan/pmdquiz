package ashtan.pmdquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ashtan.pmdquiz.R;
import ashtan.pmdquiz.model.Nature;
import ashtan.pmdquiz.model.Question;

public class MainActivity extends AppCompatActivity {

    public static Question[] questions;                 //all possible questions
    public static Map<Nature, String> pokemon;          //all possible pokemon quiz results
    public static String displayName;

    public static Integer[] selectedQs;                 //8 selected questions for curr quiz
    public static int currQNum;                         //0-7 marking curr q
    public static Map<Nature, Integer> currQuizResults; //cumulative results for curr quiz



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initQuestions();
        initPokemon();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }




    //Event Handlers
    public void start(View v) {
        initCurrQuiz();     //select 8 random q's for curr quiz
        currQNum = 0;
        initUser();         //init map keeping track of nature points

        startActivity(new Intent(MainActivity.this, QuestionActivity.class));
    }

    //Helpers
    private void initQuestions() {
        questions = new Question[25];

        questions[0] = new Question("Have you ever blurted something out without thinking about the consequences first?",
                "Yes.", "No.", "Lonely 2,Relaxed 2", "Hardy 1");
        questions[1] = new Question("Do you want to be taller someday?",
                "Totally!", "Of course not.", "Sassy 2", "Calm 1");
        questions[2] = new Question("Once you've decided something, do you see it through to the end?",
                "Yes.", "No.", "Hardy 2", "Quirky 2");
        questions[3] = new Question("Have you ever said \"nice to meet you\" to someone you've met previously?",
                "Yes.", "No.", "Brave 2,Relaxed 1", "Calm 1");
        questions[4] = new Question("Have you ever looked at your reflection in a mirror and thought, \"What a cool person\"?",
                "Certainly!", "Well, not really...", "Jolly 1,Naive 1,Sassy 2", "Calm 1" );
        questions[5] = new Question("Have you ever thought that if you dug in your backyard you could find buried treasure?",
                "Yes.", "No.", "Naive 2", "Quiet 1");
        questions[6] = new Question("Do you prefer to play outside rather than inside?",
                "Yes.", "No.", "Bold 1,Jolly 2,Relaxed 1", "Calm 1");
        questions[7] = new Question("You discover a beat-up-looking treasure chest in some ruins. What do you do?",
                "Open it!", "Get help opening it.", "Brave 2,Hasty 2,Impish 2,Rash 1", "Timid 1");
        questions[8] = new Question("Have you ever realized you were hogging the conversation?",
                "Yes.", "No.", "Rash 2,Sassy 2", "Docile 1,Quiet 1");
        questions[9] = new Question("When you see a switch, do you feel an overwhelming urge to flip it?",
                "Yes.", "No.", "Hasty 2", "Calm 1");
        questions[10] = new Question("Have you ever forgotten you bought something and bought another one?",
                "Yes.", "No.", "Hasty 1,Quirky 2,Rash 1", "Quiet 1");
        questions[11] = new Question("Do you think it's important to always aim to be the best?",
                "Of course!", "Not really.", "Lonely 1,Sassy 1", "Calm 2,Quirky 1");
        questions[12] = new Question("Do you want to be famous?",
                "Yes.", "No.", "Lonely 2,Sassy 2", "Relaxed 1");
        questions[13] = new Question("If you saw someone doing something bad, could you scold them?",
                "Of course!", "Not really.", "Bold 1,Brave 2,Sassy 2", "Timid 1");
        questions[14] = new Question("Have you ever told a joke that just completely fell flat?",
                "Yes.", "No.", "Impish 1,Naive 2", "Calm 2");
        questions[15] = new Question("Do you like lively parties?",
                "Yes.", "No.", "Jolly 2,Lonely 1", "Quiet 1");
        questions[16] = new Question("Are you truly sincere when you apologize?",
                "Of course.", "That's not easy to admit!", "Bold 1,Docile 2", "Lonely 1,Timid 2");
        questions[17] = new Question("Do you like karaoke?",
                "Yes.", "No.", "Jolly 2,Sassy 2", "Hasty 1,Timid 1");
        questions[18] = new Question("You're hiking up a mountain when you reach diverging paths. Which kind do you take?",
                "Narrow.", "Wide.", "Impish 2,Naive 1", "Quirky 2,Timid 1");
        questions[19] = new Question("Your friend takes a spectacular fall! What do you do?",
                "Help my friend up!", "Laugh! It's too funny!", "Brave 2,Lonely 1", "Impish 2,Naive 2,Rash 1");
        questions[20] = new Question("Have you ever accidentally called a teacher \"Mom\" or \"Dad\"?",
                "Yes.", "No.", "Rash 2", "Quiet 2");
        questions[21] = new Question("There's a rumor around about a ghost haunting the school bathrooms! What do you do?",
                "Scary...bathrooms!", "Go in there anyway.", "Docile 2,Timid 1", "Bold 1,Jolly 2,Relaxed 1");
        questions[22] = new Question("Your friend is running a little late to meet you. Is that OK?",
                "Yes.", "Not at all!", "Bold 2,Relaxed 1", "Hasty 1,Lonely 2");
        questions[23] = new Question("Do you think that, no matter what, life goes on?",
                "All the time!", "Never.", "Jolly 1,Relaxed 2", "Quiet 1");
        questions[24] = new Question("Do you think blaming something you did on someone else is sometimes necessary?",
                "Of course!", "No way!", "Quiet 2,Sassy 2", "Brave 2");
    }

    private void initPokemon() {
        pokemon = new HashMap<>();

        pokemon.put(Nature.BOLD, "Turtwig");
        pokemon.put(Nature.BRAVE, "Pikachu");
        pokemon.put(Nature.CALM, "Chikorita");
        pokemon.put(Nature.DOCILE, "Charmander");
        pokemon.put(Nature.HARDY, "Torchic");
        pokemon.put(Nature.HASTY, "Shinx");
        pokemon.put(Nature.IMPISH,"Piplup");
        pokemon.put(Nature.JOLLY, "Totodile");
        pokemon.put(Nature.LONELY, "Bulbasaur");
        pokemon.put(Nature.NAIVE, "Chimchar");
        pokemon.put(Nature.QUIET, "Treecko");
        pokemon.put(Nature.QUIRKY, "Squirtle");
        pokemon.put(Nature.RASH, "Mudkip");
        pokemon.put(Nature.RELAXED, "Phanpy");
        pokemon.put(Nature.SASSY, "Riolu");
        pokemon.put(Nature.TIMID, "Cyndaquil");
    }

    private void initUser() {
        currQuizResults = new HashMap<>();

        for (Nature n : Nature.values()) {
            currQuizResults.put(n, 0);
        }
    }

    private void initCurrQuiz() {
        Random rng = new Random();
        Set<Integer> selected = new HashSet<>();

        while (selected.size() < 8) {
            selected.add(rng.nextInt(25));
        }

        selectedQs = selected.toArray(new Integer[8]);
    }

}
