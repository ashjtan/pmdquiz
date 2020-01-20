package ashtan.pmdquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ashtan.pmdquiz.R;
import ashtan.pmdquiz.model.Nature;
import ashtan.pmdquiz.model.Result;

public class ResultsActivity extends AppCompatActivity {
    private DatabaseReference resultsDb = FirebaseDatabase.getInstance().getReference("results");

    private ArrayList<Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView result = (TextView) findViewById(R.id.poke_result);
        result.setText(MainActivity.pokemon.get(maxNature()));      //sets to pokemon name

        TableLayout friendResults = (TableLayout) findViewById(R.id.friend_results);

        
        resultsDb.addValueEventListener(new ValueEventListener() {
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
        });
    }

    //Helpers

    //returns nature w/ max points
    private Nature maxNature() {
        Nature maxNature = Nature.BOLD;

        for (Nature n : Nature.values()) {
            if (MainActivity.currQuizResults.get(n) > MainActivity.currQuizResults.get(maxNature)) {
                maxNature = n;
            }
        }

        return maxNature;
    }
}
