package sistema.recursos;
import sistema.SistemaMercado;

public class Produto {
	private int informacao;
	private String codigo;
	private String nome;
	private int unidade;
	private float valorUnitarioDeCompra;
	private float valorUnitarioDeVenda;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getUnidade() {
		return unidade;
	}
	
	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}
	
	public float getValorUnitarioDeCompra() {
		return valorUnitarioDeCompra;
	}
	
	public void setValorUnitarioDeCompra(float valorUnitarioDeCompra) {
		this.valorUnitarioDeCompra = valorUnitarioDeCompra;
	}
	
	public float getValorUnitarioDeVenda() {
		return valorUnitarioDeVenda;
	}
	
	public void setValorUnitarioDeVenda(float valorUnitarioDeVenda) {
		this.valorUnitarioDeVenda = valorUnitarioDeVenda;
	}
	
	public int getInformacao() {
		return informacao;
	}
	
	public void setIdentificador(int informacao) {
		this.informacao = informacao;
	}
	
	public Produto(){
	}
	
	public Produto(String nome, SistemaMercado sistema) {
		this.nome = nome;
		this.unidade = 0;
		this.valorUnitarioDeCompra = 0;
		this.valorUnitarioDeVenda = 0;
		this.informacao = sistema.getProdutosEmEstoque().size() + 1;
		this.setCodigo(this.informacao);
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setCodigo(int informacao) {
		this.codigo = criarCodigo(informacao);
	}
	
	public static String criarCodigo(int informacao){
		return String.format("%05d", informacao);
	}
}