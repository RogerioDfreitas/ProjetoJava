package sistema.usuarios.funcionarios;

public class CaixaEletronico extends Usuario {
    public CaixaEletronico(){
    }
    public CaixaEletronico(String nome, String cargo, String login, String senha, String email, String matricula){
    	super(nome, cargo, login, senha, email, matricula);
    }
}
