package br.com.up.forca.repositories;

import java.util.ArrayList;

import br.com.up.forca.models.Palavra;

public class ForcaRepository {

    private static ForcaRepository repository;
    private ArrayList<Palavra> palavraSecreta = new ArrayList<>();

    public static ForcaRepository getInstance(){

        if(repository == null){
            repository = new ForcaRepository();
        }
        return repository;

    }

    private ForcaRepository(){ }

    public void save(Palavra palavra){
        palavraSecreta.add(palavra);
    }

    public void salvarPalavraMascarada(Palavra palavra){
        palavraSecreta.add(palavra);
    }

    public void delete(Palavra palavra){
        palavraSecreta.remove(palavra);
    }

    public ArrayList<Palavra> getAll(){
        return palavraSecreta;
    }

    public Palavra getByIndex(int index){
        return palavraSecreta.get(index);
    }

    public void update(int index, Palavra palavra){
        palavraSecreta.set(index,palavra);
    }
}

