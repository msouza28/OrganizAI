package com.example.organizaiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroActivity extends AppCompatActivity {

    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void handlerProximoIntro(View view) {
        //Atribuição de uma variavel global para obter a informação de quantas vezes o botao esta sendo clicado
        clickCount++;

        //busca pela textview que iremos alterar
        TextView viewById = findViewById(R.id.txt_intro);

        //condicional para lidar com cada um dos clicks
        switch (clickCount){
            case 1:
                viewById.setText("Queremos que você seja capaz de cuidar dos seus gastos de forma adequada, através de dicas e sugestões.");
            break;
            case 2:
                viewById.setText("Com apenas algumas perguntas, vamos elaborar sugestões personalizadas para você.");
            break;
            case 3:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}