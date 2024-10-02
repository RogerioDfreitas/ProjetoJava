package sistema;

import sistema.parteVisual.parteGrafica.TelaLogin;

import java.io.File;

import sistema.parteVisual.parteGrafica.TelaCadastrarUsuario;
import sistema.recursos.Json;

public class Arquivosistema {
    public static void main(String[] args) {
        Json json  = new Json();
        SistemaMercado sistema = json.lerJson();
        File arquivo = new File("sistemaDoMercadinho.json");
        if (arquivo.exists()){
            TelaLogin  TelaDeLogin = new TelaLogin(sistema);
            System.out.println("O arquivo existe!");
        } else {    
            System.out.println("O arquivo não existe!");
            TelaCadastrarUsuario telaDeCadastro =  new TelaCadastrarUsuario(sistema);
        }
    }   
}