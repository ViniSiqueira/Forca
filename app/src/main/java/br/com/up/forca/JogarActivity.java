package br.com.up.forca;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

//import br.com.up.forca.model.Palavra;
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
      //  String nome = inputTextNomeJogador.getText().toString();

        for(int i = 0; i < palavraEscolhida.length(); i++) {
            palavraSecreta += "_";
        }

       // Palavra palavra = new Palavra(
       //         nome,
       //         palavraEscolhida);

       // ForcaRepository.getInstance().salvarPalavraMascarada(palavra);

        textoVisual.setText(palavraSecreta);

    }

    private void adicionarLetra(){

        int tentativas = 10;
        String letras = inputTextLetra.getText().toString();

        if(letras.isEmpty()){
            inputLayoutLetra.setError("Atenção! Insira uma letra");
            return;
        }

        if(letras.length() > 1){
            inputLayoutLetra.setError("Atenção! Só pode ser inserido uma letra por vez");
            return;
        }


        if (letrasUsadas.indexOf(letras) >= 0) {
            inputLayoutLetra.setError("Atenção! Você já tentou utilizar essa letra");
            return;
        }

        letrasUsadas += letras;

        if (palavraEscolhida.indexOf(letras) >= 0){
            for(int i = 0; i < palavraEscolhida.length(); i++) {
                if (letrasUsadas.indexOf(palavraEscolhida.charAt(i)) >= 0) {
                    palavraSecreta += palavraEscolhida.charAt(i);
                } else {
                    palavraSecreta += "_";
                }
            }
        } else {
            inputLayoutLetra.setError("Atenção! Não existe essa letra na palavra secreta");
            tentativas -= tentativas;
            return;
        }


        if (!palavraSecreta.contains("_")) {
            //ganhou
        }


    }

}

