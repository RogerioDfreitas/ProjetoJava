package sistema;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sistema.recursos.*;
import sistema.recursos.Produto;
import sistema.usuarios.funcionarios.*;

public class SistemaMercado {
	private ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<Produto> produtosEmEstoque = new ArrayList<>();
	private ArrayList<Salvar> registrosDeCompra = new ArrayList<>();
	private ArrayList<Salvar> registrosDeVenda = new ArrayList<>();
	private ArrayList<Cupom> cupons = new ArrayList<>();

	
	public boolean gerenteExiste() {
        return listaDeUsuarios.isEmpty();
    }

	public Produto acharProduto(String nome){
		for (Produto produto : this.getProdutosEmEstoque()){
			if(produto.getNome().equalsIgnoreCase(nome)){
				return produto;
			}
		}
		return null;
	}

	public Cliente buscar(String cpf){
		for(Cliente cliente : this.getClientes()){
			if(cliente.getCpf().equals(cpf)){
				return cliente;
			}
		}
		return null;
	}

	public Usuario buscarUsuario(String login, String senha) {
		for (Usuario usuario : this.getListaDeUsuarios()) {
			if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
				 return usuario;
			}
		}
		return null;
	}

	public Produto buscarCodigo(String codigo){
		for (Produto produto : this.getProdutosEmEstoque()){
			if(produto.getCodigo().equals(codigo)){
				return produto;
			}
		}
		return null;
	}

	public Cupom validarCupom(String codigo){
		for(Cupom cupom : this.getCupons()){
			if(cupom.getCodigo().equals(codigo)){
				return cupom;
			}
		}
		return null;
	}

	public float TotalDeCompras(){
		float total = 0;
		for (Salvar registro : registrosDeCompra){
			total += registro.getValor() * registro.getUnidades();
		}
		return total;
	}

	public float TotalDeVendas(){
		float total = 0;
		for (Salvar registro : registrosDeVenda){
			total += registro.getValor() * registro.getUnidades();
		}
		return total;
	}

	public float TotalApurado(){
		return TotalDeVendas() - TotalDeCompras();
	}

	public ArrayList<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(ArrayList<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public ArrayList<Produto> getProdutosEmEstoque() {
		return produtosEmEstoque;
	}

	public void setProdutosEmEstoque(ArrayList<Produto> produtosEmEstoque) {
		this.produtosEmEstoque = produtosEmEstoque;
	}
	public ArrayList<Cliente> getClientes(){
		return clientes;
	}
	
	public void setClientes(ArrayList<Cliente> clientes){
		this.clientes = clientes;
	}

	public ArrayList<Salvar> getRegistrosDeVenda() {
		return registrosDeVenda;
	}

	public void setRegistrosDeVenda(ArrayList<Salvar> registrosDeVenda) {
		this.registrosDeVenda = registrosDeVenda;
	}

	public ArrayList<Salvar> getRegistrosDeCompra() {
		return registrosDeCompra;
	}

	public void setRegistrosDeCompra(ArrayList<Salvar> registrosDeCompra) {
		this.registrosDeCompra = registrosDeCompra;
	}

	public ArrayList<Cupom> getCupons(){
		return cupons;
	}

	public void setCupons(ArrayList<Cupom> cupons){
		this.cupons = cupons;
	}


}