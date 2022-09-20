package br.com.up.forca;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.up.forca.models.Palavra;
import br.com.up.forca.repositories.ForcaRepository;

public class JogarActivity extends AppCompatActivity  {

    private TextView textoVisual;
    private FloatingActionButton buttonAdicionarLetra;
    private TextInputLayout inputLayoutLetra;
    private TextInputEditText inputTextLetra;
    private TextInputEditText inputTextNomeJogador;

    String palavraEscolhida = "";
    String palavraSecreta = "";
    String letrasUsadas = "";
    int tentativas = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        textoVisual = findViewById(R.id.textViewPalavra);
        buttonAdicionarLetra = findViewById(R.id.buttonAddLetra);
        inputLayoutLetra = findViewById(R.id.inputLayoutLetra);
        inputTextLetra = findViewById(R.id.inputTextLetra);


        carregarPalavraSorteada();

        buttonAdicionarLetra.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adicionarLetra();
                    }
                });

    }

    private void carregarPalavraSorteada() {

        palavraEscolhida = "CARRO";

        for(int i = 0; i < palavraEscolhida.length(); i++) {
            palavraSecreta += "_";
        }

        Palavra palavra = new Palavra(
                palavraEscolhida,
                tentativas);

       ForcaRepository.getInstance().salvarPalavraMascarada(palavra);

        textoVisual.setText(palavraSecreta);

    }

    private void adicionarLetra() {

        String letras = inputTextLetra.getText().toString();
        letras = letras.toUpperCase();

        if(letras.isEmpty()){
            inputLayoutLetra.setError("Atenção! Insira uma letra");
            return;
        }

        if(letras.length() > 1){
            inputTextLetra.setText("");
            inputLayoutLetra.setError("Atenção! Só pode ser inserido uma letra por vez");
            return;
        }


        if (letrasUsadas.indexOf(letras) >= 0) {
            inputTextLetra.setText("");
            inputLayoutLetra.setError("Atenção! Você já tentou utilizar essa letra");
            return;
        }

        letrasUsadas += letras;
        palavraSecreta = "";

        if (tentativas > 0) {
            if (palavraEscolhida.indexOf(letras) >= 0) {
                for (int i = 0; i < palavraEscolhida.length(); i++) {
                    if (letrasUsadas.indexOf(palavraEscolhida.charAt(i)) >= 0) {
                        palavraSecreta += palavraEscolhida.charAt(i);
                    } else {
                        palavraSecreta += "_";
                    }
                }
            } else {
                inputLayoutLetra.setError("Atenção! Não existe essa letra na palavra secreta");
                tentativas -= 1;
                return;
            }

            if (!palavraSecreta.contains("_")) {
                //ganhou
            }
        } else {
            inputLayoutLetra.setError("Atenção! Você perdeu!");
            //colocar timer;
            onBackPressed();
            return;
        }

        textoVisual.setText(palavraSecreta);
        inputTextLetra.setText("");
    }

}

