package senac.batismo.models;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeraNomes {
    private String tipo;
    private int quantidade;
    private List<Nome> nomes;

    public GeraNomes(String tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        nomes = new ArrayList<Nome>();
    }

    public GeraNomes() {
        nomes = new ArrayList<Nome>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Nome> getNomes() {
        return nomes;
    }

    public void setNomes() {
        Faker gerador = new Faker(new Locale("pt-BR"));

        for(int i=0; i < quantidade; i++)
            nomes.add(new Nome(tipo));
    }
}
