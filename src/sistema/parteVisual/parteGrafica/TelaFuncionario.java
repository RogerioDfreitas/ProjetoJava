package sistema.parteVisual.parteGrafica;

import javax.swing.JButton;
import javax.swing.JPanel;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteFuncionario;
import sistema.usuarios.funcionarios.Usuario;

public class TelaFuncionario extends TelaUsuario {
    private JButton listaProdutos;
    private JButton cadastrarProduto;

    public JButton getListarProdutos(){
        return listaProdutos;
    }

    public void setListarProdutos(JButton listarProdutos) {
        this.listaProdutos = listarProdutos;
    }

    public JButton getCadastrarProduto(){
        return cadastrarProduto;
    }

    public void setCadastrarProduto(JButton cadastrarProduto) {
        this.cadastrarProduto = cadastrarProduto;
    }

    public TelaFuncionario(SistemaMercado sistema, Usuario usuario){
        super(sistema, usuario);
        add(adicionarTitulo("Bem-vindo(a), " + usuario.getNome() + "."));

        cadastrarProduto = adicionarBotao("Registrar Produto", getFonteDoBotao(), getPainelBotoes(), null);
        listaProdutos = adicionarBotao("Lista De Produtos", getFonteDoBotao(), getPainelBotoes(), null);

        OuvinteFuncionario ouvinteFuncionario = new OuvinteFuncionario(this, sistema, usuario);
        listaProdutos.addActionListener(ouvinteFuncionario);
        cadastrarProduto.addActionListener(ouvinteFuncionario);
        setVisible(true);
    }

    
}