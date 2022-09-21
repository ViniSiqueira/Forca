package br.com.up.forca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.up.forca.adapters.ForcaAdapter;
import br.com.up.forca.models.Palavra;
import br.com.up.forca.repositories.ForcaRepository;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonJogar;
    private TextInputEditText inputTextNomeJogador;
    private RecyclerView recyclerViewPalavras;
    private TextView textNomeJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonJogar = findViewById(R.id.buttonPlay);
        inputTextNomeJogador = findViewById(R.id.inputTextNome);
        recyclerViewPalavras = findViewById(R.id.recyclerForca);
        textNomeJogo = findViewById(R.id.textViewNomeJogo);

        recyclerViewPalavras.setLayoutManager(
              //   new GridLayoutManager(this,10)
                new LinearLayoutManager(this,
                        RecyclerView.VERTICAL,
                        false)
        );

       buttonJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = inputTextNomeJogador.getText().toString();

                if (nome.isEmpty()){
                    inputTextNomeJogador.setError("Atenção! Insira seu nome");
                    return;
                }

                Intent intent = new Intent(
                        getApplicationContext(),
                        JogarActivity.class
                );
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Palavra> palavras =
                ForcaRepository.getInstance().getAll();

        if(palavras.size() > 0){
            textNomeJogo.setVisibility(View.INVISIBLE);
        }else{
            textNomeJogo.setVisibility(View.VISIBLE);
        }

        recyclerViewPalavras.setAdapter(
                new ForcaAdapter(palavras)
        );

    }
}