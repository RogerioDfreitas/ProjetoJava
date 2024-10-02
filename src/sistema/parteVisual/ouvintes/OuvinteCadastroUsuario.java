package sistema.parteVisual.ouvintes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import sistema.SistemaMercado;
import sistema.parteVisual.parteGrafica.TelaLogin;
import sistema.parteVisual.parteGrafica.TelaCadastrarUsuario;
import sistema.recursos.Json;
import sistema.usuarios.funcionarios.Almoxarife;
import sistema.usuarios.funcionarios.CaixaEletronico;
import sistema.usuarios.funcionarios.Gerente;
import sistema.usuarios.funcionarios.Usuario;

public class OuvinteCadastroUsuario extends OuvinteDeVerificacao {
    private TelaCadastrarUsuario tela;
    private JRadioButton checkAlmoxarife;
    private JRadioButton checkCaixa;
    private SistemaMercado sistema;

    public OuvinteCadastroUsuario(TelaCadastrarUsuario tela, SistemaMercado sistema) {
        super(tela, sistema);
        this.tela = tela;
        this.sistema = sistema;
        this.checkAlmoxarife = tela.getCheckAlmoxarife();
        this.checkCaixa = tela.getCheckCaixa();
    }

    @Override
    protected void confirmar() {
        try {
            cadastrar(sistema.getListaDeUsuarios());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getCargoEscolhido() {
        if (sistema.gerenteExiste()) {
            return "Gerente";
        } 
        else if (checkAlmoxarife.isSelected()) {
            return "Almoxarife";
        } 
        else if (checkCaixa.isSelected()) {
            return "Caixa Eletronico";
        }
        return "";
    }

    private void cadastrar(ArrayList<Usuario> listaDeUsuarios) throws Exception {
        String cargo = getCargoEscolhido();
        String nome = tela.getCampoDoNome().getText();
        String login = tela.getCampoDoLogin().getText();
        String senha = new String(tela.getCampoDaSenha().getPassword());
        String confirmacao = new String(tela.getCampoConfirmar().getPassword());
        String email = tela.getCampoDoEmail().getText();
        String matricula = tela.getCampoDaMatricula().getText();
        boolean senhaConfirmada = senha.equals(confirmacao);

        if (!senhaConfirmada) {
            JOptionPane.showMessageDialog(tela, "Aviso: Senhas Diferentes");
            throw new Exception("ERRO: Senhas Diferentes");
        }

        Usuario usuario;
        switch (cargo.toLowerCase()) {
            case "almoxarife":
                usuario = new Almoxarife(nome, cargo, login, senha, email, matricula);
                break;
            case "caixa eletronico":
                usuario = new CaixaEletronico(nome, cargo, login, senha, email, matricula);
                break;
            case "gerente":
                usuario = new Gerente(nome, cargo, login, senha, email, matricula);
                break;
            default:
                JOptionPane.showMessageDialog(tela, "Escolha um cargo", "Aviso", JOptionPane.ERROR_MESSAGE);
                return;
        }

        listaDeUsuarios.add(usuario);
        Json json = new Json();
        tela.dispose();
        json.criarJson(sistema);
        if (cargo.equalsIgnoreCase("gerente")) {
            TelaLogin telaDeLogin = new TelaLogin(sistema);
            JOptionPane.showMessageDialog(tela, "Cadastro concluido!");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}