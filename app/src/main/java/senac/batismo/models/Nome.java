package senac.batismo.models;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Nome {
    private String tipo;
    private String primeiro;
    private String ultimo;
    private Faker gerador;

    public Nome(String tipo){
        this.gerador = new Faker(new Locale("pt-BR"));
        this.tipo = tipo;
    }

    public void setNome(){
        switch(this.tipo){
            case "Nomes":
                primeiro = gerador.name().firstName();
                ultimo = gerador.name().lastName();
                break;

            case "Senhor dos aneis":
                primeiro = gerador.lordOfTheRings().character();
                ultimo = gerador.lordOfTheRings().location();

            case "Game of thrones":
                primeiro = gerador.gameOfThrones().character();
                ultimo = gerador.gameOfThrones().city();
                break;

            case "Witcher":
                primeiro = gerador.witcher().character();
                ultimo = gerador.witcher().location();
                break;
        }
    }

    public String getPrimeiro() {
        return primeiro;
    }

    public String getUltimo() {
        return ultimo;
    }
}
