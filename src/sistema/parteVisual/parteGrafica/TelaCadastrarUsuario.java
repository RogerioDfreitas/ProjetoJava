package sistema.parteVisual.parteGrafica;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteCadastroUsuario;

public class TelaCadastrarUsuario extends TelaPadrao { 
    private JTextField campoDoNome;
    private JTextField campoDoLogin;
    private JPasswordField campoDaSenha;
    private JPasswordField campoConfirmar;
    private JTextField campoDoEmail;
    private JTextField campoDaMatricula;
    private JRadioButton checkAlmoxarife;
    private JRadioButton checkCaixa;

    public JTextField getCampoDoNome() {
        return campoDoNome;
    }

    public void setCampoDonome(JTextField campoDonome) {
        this.campoDoNome = campoDonome;
    }

    public JTextField getCampoDoLogin() {
        return campoDoLogin;
    }

    public void setCampoDoLogin(JTextField campoDoLogin) {
        this.campoDoLogin = campoDoLogin;
    }

    public JPasswordField getCampoDaSenha() {
        return campoDaSenha;
    }

    public void setCampoDaSenha(JPasswordField campoDaSenha) {
        this.campoDaSenha = campoDaSenha;
    }

    public void setCampoConfirmar(JPasswordField campoConfirmar){
        this.campoConfirmar = campoConfirmar;
    }

    public JPasswordField getCampoConfirmar(){
        return campoConfirmar;
    }

    public JTextField getCampoDoEmail() {
        return campoDoEmail;
    }

    public void setCampoDoEmail(JTextField campoDoEmail) {
        this.campoDoEmail = campoDoEmail;
    }

    public JTextField getCampoDaMatricula() {
        return campoDaMatricula;
    }

    public void setCampoDaMatricula(JTextField campoDaMatricula) {
        this.campoDaMatricula = campoDaMatricula;
    }

    public JRadioButton getCheckAlmoxarife() {
        return checkAlmoxarife;
    }


    public void setCheckAlmoxarife(JRadioButton checkAlmoxarife) {
        this.checkAlmoxarife = checkAlmoxarife;
    }


    public JRadioButton getCheckCaixa() {
        return checkCaixa;
    }


    public void setCheckCaixa(JRadioButton checkCaixa) {
        this.checkCaixa = checkCaixa;
    }


    public void setCampoDoNome(JTextField campoDoNome) {
        this.campoDoNome = campoDoNome;
    }

    public TelaCadastrarUsuario(SistemaMercado sistema){
        super(sistema);
        setSize(600, 540); 
        setLayout(null);
        setLocationRelativeTo(null);

        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();
        JPanel painelCheckers = new JPanel();

        painelTextos.setLayout(new GridLayout(6, 1, 0, 20));
        painelCampos.setLayout(new GridLayout(6, 1, 0, 20));
        painelBotoes.setLayout(new GridLayout(1, 2, 100, 0));
        painelCheckers.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        
        painelTextos.setBounds(50, 100, 100, 300);
        painelCampos.setBounds(150, 100, 400, 300);
        painelBotoes.setBounds(150, 420, 400, 50);
        painelCheckers.setBounds(300, 70, 250, 30);

        JLabel textoNome = new JLabel("Nome:");
        JLabel textoLogin = new JLabel("Login:");
        JLabel textoSenha = new JLabel("Senha:");
        JLabel textoConfirmar = new JLabel("Confirmar:");
        JLabel textoEmail = new JLabel("E-mail:");
        JLabel textoMatricula = new JLabel("Pis/NIS: ");

        campoDoNome = new JTextField(30);
        campoDoLogin = new JTextField(15);
        campoDaSenha = new JPasswordField(15);
        campoConfirmar = new JPasswordField(15);
        campoDoEmail = new JTextField(30);
        try {
            MaskFormatter mascaraMatricula = new MaskFormatter("***.*****.**-*");
            mascaraMatricula.setPlaceholderCharacter('_');
            campoDaMatricula = new JFormattedTextField(mascaraMatricula);
        } catch (ParseException e) {
            e.printStackTrace();
            campoDaMatricula = new JTextField();
        }
        campoDoEmail.setEnabled(true);
        campoDaMatricula.setEnabled(true);
        checkAlmoxarife = new JRadioButton("Almoxarife", false);
        checkCaixa = new JRadioButton("Caixa Eletronico", false);

        ButtonGroup grupoCheckers = new ButtonGroup();
        grupoCheckers.add(checkAlmoxarife);
        grupoCheckers.add(checkCaixa);
        
        setBotaoConfirmar(new JButton("Cadastrar"));
        setBotaoCancelar(new JButton("Cancelar"));

        JTextField[] Campos = {campoDoNome,campoDoLogin, campoDaSenha, campoConfirmar,  campoDoEmail,campoDaMatricula};

        JComponent[] Textos = {textoNome, textoLogin, textoSenha, textoConfirmar, textoEmail, textoMatricula};

        JComponent[] Botoes = {getBotaoConfirmar(), getBotaoCancelar()};  

        JComponent[] Checkers = {checkAlmoxarife, checkCaixa};

        setCampos(Campos);
        adicionarFontes(Textos);
        adicionarFontes(Campos);
        adicionarFontes(Botoes);
        adicionarAoPainel(Botoes, painelBotoes);
        adicionarAoPainel(Campos, painelCampos);
        adicionarAoPainel(Textos, painelTextos);
        adicionarAoPainel(Checkers, painelCheckers);
        add(painelTextos);
        add(painelCampos);
        add(painelCheckers);
        add(painelBotoes);
     
        

        if(sistema.gerenteExiste()){
            adicionarTitulo("Registrar Gerente");
            checkAlmoxarife.setVisible(false);
            checkCaixa.setVisible(false);
        }else{
            adicionarTitulo("Registrar Funcionário");
        }


        OuvinteCadastroUsuario ouvinteCadastro = new OuvinteCadastroUsuario(this, sistema);
        
        getBotaoConfirmar().addActionListener(ouvinteCadastro);
        getBotaoCancelar().addActionListener(ouvinteCadastro);
        getCheckAlmoxarife().addActionListener(ouvinteCadastro);
        getCheckCaixa().addActionListener(ouvinteCadastro);
        


        setVisible(true);
    }
}