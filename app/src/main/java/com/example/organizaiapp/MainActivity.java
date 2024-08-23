package com.example.organizaiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConstraintLayout constraintLayout = findViewById(R.id.balanco_constraint);
        View bgSelected = findViewById(R.id.bg_selected);
        TextView receitaTitulo = findViewById(R.id.txt_receita_titulo);
        TextView despesaTitulo = findViewById(R.id.txt_despesa_titulo);

        receitaTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Realiza a animação
                TransitionManager.beginDelayedTransition(constraintLayout);
                // Modifica as constraints para vincular o bg_selected ao título Receitas
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.TOP, R.id.txt_receita_titulo, ConstraintSet.TOP);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.BOTTOM, R.id.txt_receita_titulo, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.START, R.id.txt_receita_titulo, ConstraintSet.START);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.END, R.id.txt_receita_titulo, ConstraintSet.END);
                constraintSet.applyTo(constraintLayout);
            }
        });

        despesaTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Realiza a animação
                TransitionManager.beginDelayedTransition(constraintLayout);
                // Modifica as constraints para vincular o bg_selected ao título Despesas
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.TOP, R.id.txt_despesa_titulo, ConstraintSet.TOP);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.BOTTOM, R.id.txt_despesa_titulo, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.START, R.id.txt_despesa_titulo, ConstraintSet.START);
                constraintSet.connect(R.id.bg_selected, ConstraintSet.END, R.id.txt_despesa_titulo, ConstraintSet.END);
                constraintSet.applyTo(constraintLayout);
            }
        });
    }
}