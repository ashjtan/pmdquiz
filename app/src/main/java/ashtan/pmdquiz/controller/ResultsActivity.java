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
    private DatabaseReference resultsRef = FirebaseDatabase.getInstance().getReference("results");

    private ArrayList<Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //calculate result
        Result currQuizResult = new Result(MainActivity.displayName,
                MainActivity.pokemon.get(maxNature()));


        TextView result = (TextView) findViewById(R.id.poke_result);
        result.setText(currQuizResult.pokemon);      //sets to pokemon name

        TableLayout friendResults = (TableLayout) findViewById(R.id.friend_results);

        //load all results from db
        resultsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    Result curr = resultSnapshot.getValue(Result.class);
                    results.add(curr);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("loadResults:onCancelled", databaseError.toException());
            }
        });

        //add results to view
        for (Result r : results) {
            addFriendResult(r.displayName, r.pokemon, friendResults);
        }

        //add currQuizResult to db
        String id = resultsRef.push().getKey();
        resultsRef.child(id).setValue(currQuizResult);
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

    private void addFriendResult(String displayId, String pokemon, TableLayout tl) {
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView col1 = new TextView(this);
        col1.setText(displayId);
        col1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(col1);

        TextView col2 = new TextView(this);
        col2.setText(pokemon);
        col2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(col2);

        tl.addView(tr);
    }
}
