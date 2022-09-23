package br.com.up.forca;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import br.com.up.forca.models.Palavra;
import br.com.up.forca.repositories.ForcaRepository;

public class JogarActivity extends AppCompatActivity  {

    private TextView textoVisual;
    private TextView textViewNumTentativas;
    private FloatingActionButton buttonAdicionarLetra;
    private TextInputLayout inputLayoutLetra;
    private TextInputEditText inputTextLetra;
    private TextInputEditText inputTextNomeJogador;

    String palavraEscolhida = "";
    String palavraSecreta = "";
    String letrasUsadas = "";
    String nomeJogador = "";
    int tentativas = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        nomeJogador = getIntent().getStringExtra("nomeJogador");


        textoVisual = findViewById(R.id.textViewPalavra);
        buttonAdicionarLetra = findViewById(R.id.buttonAddLetra);
        inputLayoutLetra = findViewById(R.id.inputLayoutLetra);
        inputTextLetra = findViewById(R.id.inputTextLetra);
        textViewNumTentativas= findViewById(R.id.textViewNumTentativas);

        carregarPalavraSorteada();
        carregarPalavraSecreta();

        textoVisual.setText(palavraSecreta);
        textViewNumTentativas.setText(Integer.toString(tentativas));

        buttonAdicionarLetra.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            adicionarLetra();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void carregarPalavraSorteada() {

        Random aleatorio = new Random();

        String[] bancoPalavras = {"CARRO",
                "AVIAO",
                "BARCO",
                "JETTA",
                "CIVIC",
                "PRISMA",
                "UNO",
                "PRYSZIADA",
                "PALMAS",
                "ESCOBAR",
                "JEAN",
                "VINICIUS",
                "ANA",
                "GABRIEL",
                "FABIO",
                "ANDROID",
                "STUDIO",
                "JAVA",
                "POSITIVO",
                "UNIVERSIDADE"};

        int idx = aleatorio.nextInt(bancoPalavras.length);
        palavraEscolhida= (bancoPalavras[idx]);

    }

    private void carregarPalavraSecreta() {

        for(int i = 0; i < palavraEscolhida.length(); i++) {
            palavraSecreta += "_";
        }

    }

    private void adicionarLetra() throws InterruptedException {

        String letras = inputTextLetra.getText().toString();
        letras = letras.toUpperCase();

        inputTextLetra.getText().clear();

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
                textViewNumTentativas.setText(Integer.toString(tentativas));
                return;
            }

            inputLayoutLetra.setError("");
            textoVisual.setText(palavraSecreta);

            if (!palavraSecreta.contains("_")) {

                Toast.makeText(this,"Atenção! Você Ganhou!!!!!", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                },4000);

                Palavra palavra = new Palavra(
                        nomeJogador,
                        palavraEscolhida,
                        tentativas);

                ForcaRepository.getInstance().save(palavra);

                onBackPressed();

            }
        } else {
            Toast.makeText(this,"Atenção! Você perdeu!", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onBackPressed();
                }
            },4000);

        }

    }

}

