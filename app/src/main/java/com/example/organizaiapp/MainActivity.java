package com.example.organizaiapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.TransitionManager;

import com.example.organizaiapp.domain.Registro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private int dataSelecionada;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //criacao a barra de rolagem para meses do ano
        final HorizontalScrollView[] horizontalScrollView = {findViewById(R.id.horizontal_scroll_view)};
        LinearLayout linearLayout = findViewById(R.id.linear_meses);
        Drawable connersRounded = ContextCompat.getDrawable(this, R.drawable.banner_conners_rounded);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.saira);
        String[] meses = {"JAN/24", "FEV/24", "MAR/24","ABR/24","MAI/24","JUN/24", "JUL/24","AGO/24", "SET/24", "OUT/24", "NOV/24", "DEZ/24",
                "JAN/25", "FEV/25", "MAR/25","ABR/25","MAI/25", "JUN/25","JUL/25","AGO/25", "SET/25", "OUT/25", "NOV/25", "DEZ/25"};
        Calendar calendar = Calendar.getInstance();
        int mesAtual = calendar.get(Calendar.MONTH);
        int anoAtual = calendar.get(Calendar.YEAR);
        final TextView[] selectedTextView = {null}; // Variável para armazenar a data selecionada
        for (int i = 0; i < meses.length; i++) {
            TextView textView = new TextView(this);
            textView.setId(i < 12 ? (anoAtual * 100 + i + 1) : ((anoAtual + 1) * 100 + (i - 11))); // Gera um ID automático
            textView.setTag(meses[i]); // Armazena o nome do mês como uma tag
            textView.setText(meses[i]);
            textView.setTextSize(18);
            textView.setPadding(35, 0, 35, 0);
            textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            textView.setTypeface(typeface, Typeface.BOLD);

            // Verificar se o TextView corresponde ao mês atual
            if ((i % 12) == mesAtual && (i < 12 && anoAtual == 2024 || i >= 12 && anoAtual == 2025)) {
                textView.setBackground(connersRounded);
                textView.setTextColor(Color.WHITE);
                selectedTextView[0] = textView; // Define o mês atual como selecionado inicialmente
                textView.post(() -> horizontalScrollView[0].smoothScrollTo(textView.getLeft(), 0));
                setDataSelecionada(textView.getId());
            }

            // Adicionar OnClickListener para alterar o background quando a data for clicada
            textView.setOnClickListener(v -> {
                if (selectedTextView[0] != null) {
                    // Reseta o background da data previamente selecionada
                    selectedTextView[0].setBackground(null); // Remove o background ou substitua por outro estilo
                    selectedTextView[0].setTextColor(Color.BLACK); // Restaura a cor do texto original
                }

                // Define o novo TextView como selecionado
                textView.setBackground(connersRounded);
                textView.setTextColor(Color.WHITE);
                selectedTextView[0] = textView; // Atualiza a variável para a nova seleção
                setDataSelecionada(textView.getId());
            });
            linearLayout.addView(textView);
        }
        // -----------------------------------------------------------------------------------------
        // Criação dos registros
        ScrollView verticalScrollView = findViewById(R.id.vertical_scroll_view);
        LinearLayout linearRegistros = findViewById(R.id.linear_registros);
        TextView txtRegistroVazio = findViewById(R.id.txt_registro_vazio);

        //Esses dados vao vir da base
        List<Registro> registros = new ArrayList<Registro>();
        registros.add(new Registro("Vaigem", "Fim de semana em Ubatuba", 700.50, 0));
        registros.add(new Registro("Carro", "Lanterna traseira", 230.00, 0));
        registros.add(new Registro("Vaigem", "Fim de semana em Ubatuba", 700.50, 0));
        registros.add(new Registro("Carro", "Lanterna traseira", 230.00, 0));
        registros.add(new Registro("Vaigem", "Fim de semana em Ubatuba", 700.50, 0));
        registros.add(new Registro("Carro", "Lanterna traseira", 230.00, 0));
        registros.add(new Registro("Vaigem", "Fim de semana em Ubatuba", 700.50, 0));

        if (!registros.isEmpty()){
            txtRegistroVazio.setVisibility(View.INVISIBLE);
            verticalScrollView.setVisibility(View.VISIBLE);
            for (Registro registro : registros){
                LayoutInflater inflater = LayoutInflater.from(this);
                LinearLayout card = (LinearLayout) inflater.inflate(R.layout.registro_card, linearRegistros, false);

                ImageView iconCategoria = card.findViewById(R.id.icon_categoria);
                TextView nomeCategoria = card.findViewById(R.id.nome_categoria);
                TextView descricaoGasto = card.findViewById(R.id.descricao_gasto);
                TextView valorGasto = card.findViewById(R.id.valor_gasto);

                nomeCategoria.setText(registro.getCategoria());

                // Defina a descrição e o valor do gasto
                descricaoGasto.setText(registro.getDescricao());
                valorGasto.setText("R$ " + registro.getValor());

                // Adicione o card ao LinearLayout
                linearRegistros.addView(card);
            }
        }

        //------------------------------------------------------------------------------------------
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
                int colorVerdeForte = ContextCompat.getColor(v.getContext(), R.color.verdeForte);
                bgSelected.setBackgroundTintList(ColorStateList.valueOf(colorVerdeForte));
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
                bgSelected.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
        });
    }

    public int getDataSelecionada() {
        Log.d("getDataSelecionada", "ID da TextView: " + dataSelecionada);
        return dataSelecionada;
    }

    public void setDataSelecionada(int dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }
}