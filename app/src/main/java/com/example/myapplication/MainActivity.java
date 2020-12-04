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


        //----------Metodo para leer la pregunta--------------
        readQuestion();
    }

    private void readQuestion() {
        //----------Entro a la rama------------------
        DatabaseReference ref = db.getReference().child("question").child("actual");

        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    //------------Leo la pregunta que esta en el firebase------------
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
        switch (v.getId()){
            case R.id.okBtn:
                //-----------Entro a la rama-------------------
                DatabaseReference ref = db.getReference().child("question").child("actual").child("puntaje");

                //----------Creo objeto del puntaje--------------
                Puntaje puntajes = new Puntaje(
                        theId,
                        puntaje.getText().toString()
                );
                //-----------Envio al firebase----------
                ref.setValue(puntajes);
                puntaje.setText(" ");
                break;


        }
    }
}