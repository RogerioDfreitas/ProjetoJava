package sistema.usuarios.funcionarios;

public abstract class Usuario extends Pessoa{
	protected String funcao;
	protected String login;
	protected String senha;
	protected String matricula;

	public Usuario(){

	}
	public Usuario(String nome, String funcao, String login, String senha, String email, String matricula){
		super(nome, email);
		this.funcao = funcao;
		this.login = login;
		this.senha = senha;
		this.matricula = matricula;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setCargo(String funcao) {
		this.funcao = funcao;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
