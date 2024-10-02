package sistema.usuarios.funcionarios;

public abstract class Funcionario extends Usuario{
    public Funcionario(){

    }
    public Funcionario(String nome, String cargo, String login, String senha, String email, String matricula){
    	super(nome, cargo, login, senha, email, matricula);
    }
}
