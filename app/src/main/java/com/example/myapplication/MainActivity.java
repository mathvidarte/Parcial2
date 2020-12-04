package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pregunta;
    private EditText puntaje;
    private Button okBtn;
    private FirebaseDatabase db;
    private String theId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();

        pregunta = findViewById(R.id.pregunta);
        puntaje = findViewById(R.id.puntaje);
        okBtn = findViewById(R.id.okBtn);

        okBtn.setOnClickListener(this);



        readQuestion();
    }

    private void readQuestion() {
        DatabaseReference ref = db.getReference().child("question").child("actual");

        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        pregunta.setText(" ");
                        for(DataSnapshot child: data.getChildren()) {
                            QuestionList questionList = child.getValue(QuestionList.class);
                            pregunta.setText(questionList.getQuestions());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
       /* switch (v.getId()){
            case R.id.okBtn:
                theId = db.getReference().child("question").child("web").push().getKey();
                Log.d(">>>>>>>>", theId);
                String numero = puntaje.getText().toString();
                DatabaseReference ref = db.getReference().child("question").child("web").child(theId);

                Puntaje puntajes = new Puntaje(
                        theId,
                        puntaje.getText().toString()
                );
                ref.setValue(puntajes);
                break;


        }*/
    }
}