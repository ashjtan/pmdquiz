package ashtan.pmdquiz.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ashtan.pmdquiz.R;
import ashtan.pmdquiz.model.Result;

public class QuestionActivity extends AppCompatActivity {

    private DatabaseReference questionsDb = MainActivity.db.getReference("questions");

    private ArrayList<Result> questions = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView qText = (TextView) findViewById(R.id.q_text);
        Button opt1 = (Button) findViewById(R.id.opt1);
        Button opt2 = (Button) findViewById(R.id.opt2);
    }

    @Override
    protected void onStart() {
        super.onStart();

/*        resultsDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    Result curr = resultSnapshot.getValue(Result.class);
                    results.add(curr);

                    //System.out.println(results.get(0).displayName + " " + results.get(0).pokemon);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("loadResults:onCancelled", databaseError.toException());
            }
        });*/

    }

    //EVENT HANDLER
    public void select1(View v) {

    }

    public void select2(View v) {

    }
}
