package br.com.up.forca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.up.forca.models.Palavra;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonJogar;
    private TextInputEditText inputTextNomeJogador;
    private RecyclerView recyclerViewPalavras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonJogar = findViewById(R.id.buttonPlay);
        inputTextNomeJogador = findViewById(R.id.inputTextNome);

     /*   recyclerViewPalavras.setLayoutManager(
              //   new GridLayoutManager(this,10)
                new LinearLayoutManager(this,
                        RecyclerView.VERTICAL,
                        false)
        );*/

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
}