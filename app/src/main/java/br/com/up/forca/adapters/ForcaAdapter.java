package br.com.up.forca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import br.com.up.forca.R;
import br.com.up.forca.models.Palavra;

public class ForcaAdapter extends RecyclerView.Adapter<ForcaAdapter.ForcaViewHolder> {

      private ArrayList<Palavra> palavras;

    public ForcaAdapter(ArrayList<Palavra> palavras){
           this.palavras = palavras;
        }

        @NonNull
        @Override
        public ForcaViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent,
        int viewType) {

            LayoutInflater layoutInflater =
                    LayoutInflater.from(parent.getContext());

            View layout = layoutInflater.inflate(
                    R.layout.view_palavras,
                    parent,
                    false
            );

           return new ForcaViewHolder(layout);

        }

        @Override
        public void onBindViewHolder(@NonNull ForcaViewHolder holder
            , int position) {

            Palavra palavra = palavras.get(position);

            TextView textViewNomeJogador =
                   holder.itemView.findViewById(R.id.textViewNomeJogador);
            TextView textViewPalavraEscolhida =
                    holder.itemView.findViewById(R.id.textViewPalavraEscolhida);
            TextView textViewTentativas =
                    holder.itemView.findViewById(R.id.textViewTentativas);

           // textViewNomeJogador.setText(palavra.getNomeJogador());
            textViewPalavraEscolhida.setText(palavra.getPalavraEscolhida());
            textViewTentativas.setText(palavra.getTentativas());
        }

        @Override
        public int getItemCount() {
            return palavras.size();
        }

        public static class ForcaViewHolder
                extends RecyclerView.ViewHolder{

            public ForcaViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

