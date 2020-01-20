package ashtan.pmdquiz.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ashtan.pmdquiz.R;
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
}
