package senac.batismo.models;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Nome {
    private String primeiro;
    private String ultimo;

    public Nome(String tipo){
        Faker gerador = new Faker(new Locale("pt-BR"));
        switch(tipo){
            case "Nomes":
                primeiro = gerador.name().firstName();
                ultimo = gerador.name().lastName();
                break;

            case "Senhor dos aneis":
                primeiro = gerador.lordOfTheRings().character();
                ultimo = gerador.lordOfTheRings().location();

            case "Game of thrones":
                primeiro = gerador.gameOfThrones().character();
                ultimo = gerador.gameOfThrones().house();
                break;

            case "Witcher":
                primeiro = gerador.witcher().character();
                ultimo = gerador.witcher().witcher();
                break;
        }
    }

    public void setNome(String tipo){
        Faker gerador = new Faker(new Locale("pt-BR"));
        switch(tipo){
            case "Nomes":
                primeiro = gerador.name().firstName();
                ultimo = gerador.name().lastName();
                break;

            case "Senhor dos aneis":
                primeiro = gerador.lordOfTheRings().character();
                ultimo = gerador.lordOfTheRings().location();

            case "Game of thrones":
                primeiro = gerador.gameOfThrones().character();
                ultimo = gerador.gameOfThrones().house();
                break;

            case "Witcher":
                primeiro = gerador.witcher().character();
                ultimo = gerador.witcher().witcher();
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
