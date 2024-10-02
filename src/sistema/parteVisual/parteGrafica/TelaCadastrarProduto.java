package sistema.parteVisual.parteGrafica;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteDeVerificacao;
import sistema.recursos.Produto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TelaCadastrarProduto extends TelaPadrao {

    private JTextField campoNome;
    private JTextField campoCodigo;

    public TelaCadastrarProduto(SistemaMercado sistema) {
        super(sistema);
        setSize(480, 330);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();

        painelTextos.setLayout(new GridLayout(2, 1, 0, 20));
        painelCampos.setLayout(new GridLayout(2, 1, 0, 20));
        painelBotoes.setLayout(new GridLayout(1, 2, 30, 0));

        painelTextos.setBounds(40, 90, 100, 90);
        painelCampos.setBounds(140, 90, 300, 90);
        painelBotoes.setBounds(140, 210, 300, 50);

        JLabel textoCodigo = new JLabel("Codigo:");
        JLabel textoNome = new JLabel("Nome:");

        campoCodigo = new JTextField();
        campoCodigo.setEnabled(false);
        int identificador = getSistema().getProdutosEmEstoque().size() + 1;
        String novoCodigo = Produto.criarCodigo(identificador);
        campoCodigo.setText(novoCodigo);
        campoNome = new JTextField();

        JButton botaoConfirmar = new JButton("Cadastrar");
        JButton botaoCancelar = new JButton("Cancelar");

        setBotaoConfirmar(botaoConfirmar);
        setBotaoCancelar(botaoCancelar);

        JTextField[] Campos = {campoCodigo, campoNome};

        JComponent[] Textos = {textoCodigo, textoNome};

        JComponent[] Botoes = {getBotaoConfirmar(), getBotaoCancelar()};

        setCampos(Campos);
        adicionarFontes(Textos);
        adicionarFontes(Campos);
        adicionarFontes(Botoes);
        adicionarAoPainel(Botoes, painelBotoes);
        adicionarAoPainel(Campos, painelCampos);
        adicionarAoPainel(Textos, painelTextos);

        OuvinteCadastroProduto ouvinteCadastroProduto = new OuvinteCadastroProduto(this, getSistema());
        getBotaoConfirmar().addActionListener(ouvinteCadastroProduto);
        getBotaoCancelar().addActionListener(ouvinteCadastroProduto);
        campoCodigo.addKeyListener(ouvinteCadastroProduto);
        campoNome.addKeyListener(ouvinteCadastroProduto);

        add(painelTextos);
        add(painelCampos);
        add(painelBotoes);
        setVisible(true);
    }

    public JTextField getCampoDoCodigo() {
        return campoCodigo;
    }

    public void setCampoDoCodigo(JTextField campoDoCodigo) {
        this.campoCodigo = campoDoCodigo;
    }

    public JTextField getCampoDoNome() {
        return campoNome;
    }

    public void setCampoDonome(JTextField campoDonome) {
        this.campoNome = campoDonome;
    }

    public class OuvinteCadastroProduto extends OuvinteDeVerificacao {
        private TelaCadastrarProduto tela;

        public OuvinteCadastroProduto(TelaCadastrarProduto tela, SistemaMercado sistema) {
            super(tela, sistema);
            setTela(tela);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            super.actionPerformed(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        protected void confirmar() {
            String nome = getCampoDoNome().getText();
            if (getSistema().acharProduto(nome) != null) {
                JOptionPane.showMessageDialog(tela, "Produto ja Cadastrado ", "Aviso", JOptionPane.ERROR_MESSAGE);

            } else {
                Produto produto = new Produto(nome, getSistema());
                getSistema().getProdutosEmEstoque().add(produto);
                JOptionPane.showMessageDialog(tela, "Produto cadastrado!\n\nCodigo: " + produto.getCodigo() + "\nNome: " + produto.getNome());
                tela.dispose();
            }
        }

        public TelaCadastrarProduto getTela() {
            return tela;
        }

        public void setTela(TelaCadastrarProduto tela) {
            this.tela = tela;
        }
    }
}