package sistema.parteVisual.parteGrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteDeVerificacao;
import sistema.usuarios.funcionarios.Cliente;

public class TelaCadastrarCliente extends TelaPadrao {
    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoEmail;
    private JTextField campoEndereco;

    public TelaCadastrarCliente(SistemaMercado sistema) {
        super(sistema);
        setSize(600, 450);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        try {
            MaskFormatter mascaraCPF = new MaskFormatter("***.***.***.**");
            mascaraCPF.setPlaceholderCharacter('_');
            campoCPF = new JFormattedTextField(mascaraCPF);
        } catch (ParseException e) {
            e.printStackTrace();
            campoCPF = new JTextField();
        }

        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();

        painelTextos.setLayout(new GridLayout(4, 1, 0, 20));
        painelCampos.setLayout(new GridLayout(4, 1, 0, 20));
        painelBotoes.setLayout(new GridLayout(1, 2, 100, 0));

        painelTextos.setBounds(50, 100, 100, 200);
        painelCampos.setBounds(150, 100, 400, 200);
        painelBotoes.setBounds(150, 320, 400, 50);

        JLabel textoNome = new JLabel("Nome:");
        JLabel textoCPF = new JLabel("CPF:");
        JLabel textoEmail = new JLabel("E-mail:");
        JLabel textoEndereco = new JLabel("Endereço:");

        campoNome = new JTextField(30);
        try {
            MaskFormatter mascaraCPF = new MaskFormatter("***.***.***-**");
            mascaraCPF.setPlaceholderCharacter('_');
            campoCPF = new JFormattedTextField(mascaraCPF);
        } catch (ParseException e) {
            e.printStackTrace();
            campoCPF = new JTextField();
        }
        campoEmail = new JTextField(15);
        campoEndereco = new JTextField(15);

        JButton botaoConfirmar = new JButton("Cadastrar") {
            @Override
            public void setPreferredSize(java.awt.Dimension preferredSize) {
                super.setPreferredSize(new Dimension(10, 30));
            }
        };
        JButton botaoCancelar = new JButton("Cancelar") {
            @Override
            public void setPreferredSize(java.awt.Dimension preferredSize) {
                super.setPreferredSize(new Dimension(10, 10));
            }
        };

        setBotaoConfirmar(botaoConfirmar);
        setBotaoCancelar(botaoCancelar);

        JComponent[] Textos = {textoNome, textoCPF, textoEmail, textoEndereco};

        JTextField[] Campos = {campoNome, campoCPF, campoEmail, campoEndereco};

        JComponent[] Botoes = {getBotaoConfirmar(), getBotaoCancelar()};

        setCampos(Campos);
        adicionarFontes(Textos);
        adicionarFontes(Campos);
        adicionarFontes(Botoes);
        adicionarAoPainel(Textos, painelTextos);
        adicionarAoPainel(Campos, painelCampos);
        adicionarAoPainel(Botoes, painelBotoes);

        add(painelTextos);
        add(painelCampos);
        add(painelBotoes);

        OuvinteCadastroCliente ouvinteCadastroCliente = new OuvinteCadastroCliente(this, getSistema());
        getBotaoConfirmar().addActionListener(ouvinteCadastroCliente);
        getBotaoCancelar().addActionListener(ouvinteCadastroCliente);
        setVisible(true);
    }

    public class OuvinteCadastroCliente extends OuvinteDeVerificacao {
        private TelaCadastrarCliente tela;
        private SistemaMercado sistema;

        public OuvinteCadastroCliente(TelaCadastrarCliente tela, SistemaMercado sistema) {
            super(tela, sistema);
            this.setTela(tela);
            this.sistema = sistema;
        }

        @Override
        public void actionPerformed(ActionEvent e)        {
            super.actionPerformed(e);
        }

        @Override
        public void confirmar() {
            String nome = tela.getCampoNome().getText();
            String email = tela.getCampoEmail().getText();
            String cpf = tela.getCampoCPF().getText();
            String endereco = tela.getCampoEndereco().getText();
            if (getSistema().buscar(cpf) != null) {
                JOptionPane.showMessageDialog(tela, "cliente já existe no sistema!", "Aviso", JOptionPane.ERROR_MESSAGE);
            } else {
                getSistema().getClientes().add(new Cliente(nome, email, cpf, endereco));
                tela.dispose();
                JOptionPane.showMessageDialog(tela, "Cliente cadastrado!");
            }
        }

        public TelaCadastrarCliente getTela() {
            return tela;
        }

        public void setTela(TelaCadastrarCliente tela) {
            this.tela = tela;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }
    }

    public JTextField getCampoNome() {
        return campoNome;
    }

    public void setCampoNome(JTextField campoNome) {
        this.campoNome = campoNome;
    }

    public JTextField getCampoCPF() {
        return campoCPF;
    }

    public void setCampoCPF(JTextField campoCPF) {
        this.campoCPF = campoCPF;
    }

    public JTextField getCampoEmail() {
        return campoEmail;
    }

    public void setCampoEmail(JTextField campoEmail) {
        this.campoEmail = campoEmail;
    }

    public JTextField getCampoEndereco() {
        return campoEndereco;
    }

    public void setCampoEndereco(JTextField campoEndereco) {
        this.campoEndereco = campoEndereco;
    }
}