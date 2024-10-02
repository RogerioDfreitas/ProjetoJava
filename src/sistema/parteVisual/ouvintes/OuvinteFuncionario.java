package sistema.parteVisual.ouvintes;

import java.awt.event.ActionEvent;

import sistema.SistemaMercado;
import sistema.parteVisual.parteGrafica.TelaCadastrarProduto;
import sistema.parteVisual.parteGrafica.TelaFuncionario;
import sistema.parteVisual.parteGrafica.TelaListarProdutos;
import sistema.usuarios.funcionarios.Usuario;

public class OuvinteFuncionario extends OuvinteUsuario {
    private TelaFuncionario tela;
    private Usuario usuario;
    
    public TelaFuncionario getTela() {
        return tela;
    }

    public void setTela(TelaFuncionario tela) {
        this.tela = tela;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public OuvinteFuncionario(TelaFuncionario tela, SistemaMercado sistema, Usuario usuario){
        super(tela, sistema);
        setTela(tela);
        setUsuario(usuario);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);

        if(e.getSource().equals(tela.getListarProdutos())){
            TelaListarProdutos telaListarProduto = new TelaListarProdutos(getSistema(),getUsuario());
        }
        else if(e.getSource().equals(tela.getCadastrarProduto())){
            TelaCadastrarProduto telaCadastrarProduto = new TelaCadastrarProduto(getSistema());
        }
    }
    
}
