package sistema.parteVisual.parteGrafica;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteDeVerificacao;
import sistema.recursos.Cupom;
import sistema.usuarios.funcionarios.Cliente;

import javax.swing.JScrollPane;

public class TelaDoCupon extends TelaPadrao {
    private JTextField campoCodigo;
    private JTextField campoDesconto;
    private JTextArea campoMensagem;

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public void setCampoCodigo(JTextField campoCodigo) {
        this.campoCodigo = campoCodigo;
    }

    public JTextField getCampoDesconto() {
        return campoDesconto;
    }

    public void setCampoDesconto(JTextField campoDesconto) {
        this.campoDesconto = campoDesconto;
    }

    public TelaDoCupon(SistemaMercado sistema) {
        super(sistema);
        setSize(500, 500);
        setLocationRelativeTo(null);
        

        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();


        painelTextos.setLayout(new GridLayout(2, 1, 0, 20));
        painelCampos.setLayout(new GridLayout(2, 1, 0, 20));
        painelBotoes.setLayout(new GridLayout(1, 2, 20, 0));

        painelTextos.setBounds(100, 80, 150, 80);
        painelCampos.setBounds(250, 80, 150, 80);
        painelBotoes.setBounds(100, 330, 300, 50);

        JLabel textoCodigo = new JLabel("Código:");
        campoCodigo = new JTextField(5);
        JLabel textoDesconto = new JLabel("Desconto:");
        campoDesconto = new JTextField(3);
        JLabel textoMensagem = new JLabel("Mensage:");
        campoMensagem = new JTextArea();
        campoMensagem.setLineWrap(true);
        campoMensagem.setWrapStyleWord(true);

        JScrollPane scrollMensagem = new JScrollPane(campoMensagem);
        
        
        setBotaoConfirmar(new JButton("Criar"));
        setBotaoCancelar(new JButton("Cancelar"));
    
        JComponent[] Texto = {textoCodigo, textoDesconto, textoMensagem};
        JTextField[] Campo = {campoCodigo, campoDesconto};
        JComponent[] Botao = {getBotaoConfirmar(), getBotaoCancelar()};
    
        setCampos(Campo);
        adicionarFontes(Texto);
        adicionarFontes(Campo);
        adicionarFontes(Botao);
    
        painelTextos.add(textoCodigo);
        painelTextos.add(textoDesconto);
        painelTextos.add(textoMensagem);
    
        JPanel painelCodigo = new JPanel(new BorderLayout());
        painelCodigo.add(campoCodigo, BorderLayout.BEFORE_FIRST_LINE);
    
        JPanel painelDesconto = new JPanel(new BorderLayout());
        painelDesconto.add(campoDesconto, BorderLayout.BEFORE_FIRST_LINE);
    
        JPanel painelMensagem = new JPanel(new BorderLayout());
        painelMensagem.add(scrollMensagem, BorderLayout.CENTER);
    
        painelCampos.add(painelCodigo);
        painelCampos.add(painelDesconto);
        painelCampos.add(painelMensagem);
    
        painelBotoes.add(getBotaoConfirmar());
        painelBotoes.add(getBotaoCancelar());
    
        add(painelTextos, BorderLayout.NORTH);
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

    
        OuvinteCupom ouvinteCupom = new OuvinteCupom(this, sistema);
        getBotaoConfirmar().addActionListener(ouvinteCupom);
        getBotaoCancelar().addActionListener(ouvinteCupom);
        getCampoCodigo().addKeyListener(ouvinteCupom);
        getCampoDesconto().addKeyListener(ouvinteCupom);
    
        setVisible(true);
    
    }

    public class OuvinteCupom extends OuvinteDeVerificacao {

        private TelaDoCupon tela;

        public OuvinteCupom(TelaDoCupon tela, SistemaMercado sistema) {
            super(tela, sistema);
            setTela(tela);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            super.actionPerformed(e);
        }

        @Override
        protected void confirmar() {
            String codigo = tela.getCampoCodigo().getText();
            String descontoTexto = tela.getCampoDesconto().getText();
            float desconto = Float.parseFloat(descontoTexto) / 100;
            Cupom cupom = new Cupom(codigo, desconto);
            String mensagem = campoMensagem.getText();

            if (getSistema().validarCupom(codigo) == null) {
                getSistema().getCupons().add(cupom);
                tela.dispose();
                JOptionPane.showMessageDialog(tela, "Cupom cadastrado!");
                for (Cliente cliente : getSistema().getClientes()) {
                }
            } else {
                JOptionPane.showMessageDialog(tela, "O Cupom ja foi cadastrado!");
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            JTextField campo = (JTextField) e.getSource();
            JTextField campoCodigo = tela.getCampoCodigo();
            String codigo = tela.getCampoCodigo().getText();
            String desconto = tela.getCampoDesconto().getText();
            char letra = e.getKeyChar();

            if (campo.equals(campoCodigo)) {
                if (codigo.length() > 4 || !Character.isLetterOrDigit(letra)) {
                    e.consume();
                }
            } else {
                if (desconto.length() > 1 || !Character.isDigit(letra)) {
                    e.consume();
                }
            }
        }

        public TelaDoCupon getTela() {
            return tela;
        }

        public void setTela(TelaDoCupon tela) {
            this.tela = tela;
        }
    }
}