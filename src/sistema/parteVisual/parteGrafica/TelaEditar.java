package sistema.parteVisual.parteGrafica;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteDeVerificacao;
import sistema.recursos.Produto;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TelaEditar extends TelaPadrao{
    
    private JTextField campoCodigo;
    private JTextField campoNome;
    private JTextField campoUnidade;
    private JTextField campoValorDeCompra;
    private JTextField campoValorDeVenda;
    private Produto produto;
    private TelaListarProdutos telaTabela;
    private int linha;

    
    public JTextField getCampoNome() {
        return campoNome;
    }

    public void setCampoNome(JTextField campoNome) {
        this.campoNome = campoNome;
    }

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public void setCampoCodigo(JTextField campoCodigo) {
        this.campoCodigo = campoCodigo;
    }

    public JTextField getCampoUnidade() {
        return campoUnidade;
    }

    public void setCampoUnidade(JTextField campoUnidade) {
        this.campoUnidade = campoUnidade;
    }

    public JTextField getCampoValorDeCompra() {
        return campoValorDeCompra;
    }

    public void setCampoValorDeCompra(JTextField campoValorDeCompra) {
        this.campoValorDeCompra = campoValorDeCompra;
    }

    public JTextField getCampoValorDeVenda() {
        return campoValorDeVenda;
    }

    public void setCampoValorDeVenda(JTextField campoValorDeVenda) {
        this.campoValorDeVenda = campoValorDeVenda;
    }

    public TelaListarProdutos getTelaTabela() {
        return telaTabela;
    }

    public void setTelaTabela(TelaListarProdutos telaTabela) {
        this.telaTabela = telaTabela;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TelaEditar(SistemaMercado sistema){
        super(sistema);
        setSize(800, 500);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);


        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();
    
        painelTextos.setLayout(new GridLayout(5, 1, 0, 20));
        painelCampos.setLayout(new GridLayout(5, 1, 0, 20));
        painelBotoes.setLayout(new GridLayout(1, 2, 30, 0));

        painelTextos.setBounds(40, 100, 160, 240);
        painelCampos.setBounds(210, 100, 300, 240);
        painelBotoes.setBounds(210, 365, 300, 50);

        JLabel textoCodigo = new JLabel("Codigo:");
        JLabel textoNome = new JLabel("Nome:");
        JLabel textoUnidades = new JLabel("Unidades:");
        JLabel textoValorCompra = new JLabel("Valor de Compra: ");
        JLabel textoValorVenda = new JLabel("Valor de Venda: ");

        campoCodigo = new JTextField();
        campoCodigo.setEnabled(false);
        campoNome = new JTextField();
        campoUnidade = new JTextField();
        campoValorDeCompra = new JTextField();
        campoValorDeVenda = new JTextField();

        setBotaoConfirmar(new JButton("Editar"));
        getBotaoConfirmar().setBackground(Color.WHITE);
        
        setBotaoCancelar(new JButton("Cancelar"));
        getBotaoCancelar().setBackground(Color.WHITE);

        JTextField[] Campos = {campoCodigo, campoNome, campoUnidade, campoValorDeCompra, campoValorDeVenda};

        JComponent[] Textos = { textoCodigo, textoNome, textoUnidades, textoValorCompra, textoValorVenda};

        JComponent[] Botoes = {getBotaoConfirmar(), getBotaoCancelar()};
        
        setCampos(Campos);
        
        adicionarAoPainel(Campos, painelCampos);
        adicionarAoPainel(Textos, painelTextos);
        adicionarAoPainel(Botoes, painelBotoes);
        adicionarFontes(Textos);
        adicionarFontes(Campos);
        adicionarFontes(Botoes);

        OuvinteEditar ouvinteEditar = new OuvinteEditar(this, sistema);
        getBotaoConfirmar().addActionListener(ouvinteEditar);
        getBotaoCancelar().addActionListener(ouvinteEditar);

        add(painelTextos);
        add(painelCampos);
        add(painelBotoes);
        setVisible(true);
    }
    
    public void receberProduto(Produto produto){
        this.produto = produto;
        this.campoCodigo.setText(produto.getCodigo());
        this.campoNome.setText(produto.getNome());
        this.campoUnidade.setText(String.valueOf(produto.getUnidade()));
        this.campoValorDeCompra.setText(String.valueOf(produto.getValorUnitarioDeCompra()));
        this.campoValorDeVenda.setText(String.valueOf(produto.getValorUnitarioDeVenda()));
    }

    public class OuvinteEditar extends OuvinteDeVerificacao{
        private TelaEditar tela;

        public OuvinteEditar(TelaEditar tela, SistemaMercado sistema){
            super(tela,sistema);
            this.setTela(tela);
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
            try{
                String nome = campoNome.getText();
                int unidades = Integer.parseInt(campoUnidade.getText());
                float valorCompra = Float.parseFloat(campoValorDeCompra.getText());
                float valorVenda = Float.parseFloat(campoValorDeVenda.getText());

                produto.setNome(nome);
                produto.setUnidade(unidades);
                produto.setValorUnitarioDeCompra(valorCompra);
                produto.setValorUnitarioDeVenda(valorVenda);
         
                DefaultTableModel modelo = (DefaultTableModel) getTelaTabela().getTabela().getModel();
                modelo.setValueAt(nome, linha, 1);
                modelo.setValueAt(unidades, linha, 2);
                modelo.setValueAt(valorCompra, linha, 3);
                modelo.setValueAt(valorVenda, linha, 4);
                
                telaTabela.getTabela().repaint();
                JOptionPane.showMessageDialog(tela,"Produto Editado");
                tela.dispose();

            }catch(Exception error){

            }
        }

        public TelaEditar getTela() {
            return tela;
        }

        public void setTela(TelaEditar tela) {
            this.tela = tela;
        }
    }
    


}
