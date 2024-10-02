package sistema.parteVisual.parteGrafica;

import javax.swing.JTextField;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteDeVerificacao;
import sistema.usuarios.funcionarios.Usuario;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TelaLogin extends TelaPadrao{
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private TelaGerente telaGerente;
    private TelaAlmoxarife telaAlmoxarife;
    private TelaCaixa telaCaixa;


    public TelaLogin(SistemaMercado sistema){
        super(sistema);
        setSize(500, 300); 
        setLayout(null);
        setLocationRelativeTo(null);

        
        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();
        
        painelTextos.setLayout(new GridLayout(2, 1, 0, 10));
        painelCampos.setLayout(new GridLayout(2, 1, 0, 10));
        painelBotoes.setLayout(new GridLayout(1, 2, 10, 10));

        painelTextos.setBounds(90, 85, 80, 70);
        painelCampos.setBounds(170, 85, 220,70);
        painelBotoes.setBounds(170, 175, 220, 50);

        JLabel login = new  JLabel("Login:");
        JLabel senha = new  JLabel("Senha:");

        campoLogin = new JTextField(15);
        campoSenha = new JPasswordField(15);

        setBotaoConfirmar(new JButton("Acessar"));
        setBotaoCancelar(new JButton("Cancelar"));

        JComponent[] componentesTextos = {login, senha};
        JTextField [] componentesCampos = {campoLogin, campoSenha};
        JComponent [] componentesBotoes = {getBotaoConfirmar(), getBotaoCancelar()};
        
        setFonteDoBotao(new Font("arial",Font.BOLD, 15));
        adicionarFontes(componentesBotoes);
        adicionarAoPainel(componentesTextos, painelTextos);
        adicionarAoPainel(componentesCampos, painelCampos);
        adicionarAoPainel(componentesBotoes, painelBotoes);
        adicionarFontes(componentesTextos);
        adicionarFontes(componentesCampos);


        setCampos(componentesCampos);
        add(painelTextos);
        add(painelCampos);
        add(painelBotoes);

        OuvinteLogin ouvinteLogin = new OuvinteLogin(this, sistema);
        getBotaoConfirmar().addActionListener(ouvinteLogin);
        getBotaoCancelar().addActionListener(ouvinteLogin);
        setVisible(true);
    }

    public class OuvinteLogin extends OuvinteDeVerificacao{
        private TelaLogin tela;

        public OuvinteLogin(TelaLogin tela, SistemaMercado sistema){
            super(tela, sistema);
            setTela(tela);
        }

        @Override
        public void confirmar() {
            String login = tela.getCampoLogin().getText();
            String senha = new String(tela.getCampoSenha().getPassword());
        
            Usuario usuario = getSistema().buscarUsuario(login, senha);
        
            if (usuario == null) {
                mostrarErroDeLogin();
            } else {
                tela.dispose();
                abrirTelaDeUsuario(usuario);
                mostrarMensagemDeSucesso();
            }
        }
        
        private void mostrarErroDeLogin() {
            JOptionPane.showMessageDialog(tela, "Login invalido", "Informações Incorretas", JOptionPane.ERROR_MESSAGE);
        }
        
        private void abrirTelaDeUsuario(Usuario usuario) {
            switch (usuario.getFuncao().toLowerCase()) {
                case "gerente":
                    new TelaGerente(getSistema(), usuario);
                    break;
                case "almoxarife":
                    new TelaAlmoxarife(getSistema(), usuario);
                    break;
                case "caixa eletronico":
                    new TelaCaixa(getSistema(), usuario);
                    break;
            }
        }
        
        private void mostrarMensagemDeSucesso() {
            JOptionPane.showMessageDialog(tela, "O Login foi efetuado!!");
        }
        

        @Override
        public void keyTyped(KeyEvent e) {
        
        }

        public TelaLogin getTela() {
            return tela;
        }

        public void setTela(TelaLogin tela){
            this.tela = tela;
        }

    }

    public JTextField getCampoLogin() {
        return campoLogin;
    }

    public void setCampogin(JTextField campoLogin) {
        this.campoLogin = campoLogin;
    }

    public JPasswordField getCampoSenha() {
        return campoSenha;
    }

    public void setCampoSenha(JPasswordField camposenha) {
        this.campoSenha = camposenha;
    }
}
