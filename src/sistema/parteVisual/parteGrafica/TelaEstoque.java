package sistema.parteVisual.parteGrafica;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
import sistema.recursos.Salvar;

public class TelaEstoque extends TelaPadrao{
    private JTextField campoNome;
    private JTextField campoCodigo;
    private JTextField campoUnidade;
    private JTextField campoValorCompra;
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
    
    public void setCampoValorCompra(JTextField campoValorCompra) {
        this.campoValorCompra = campoValorCompra;
    }
    
    public JTextField getCampoValorCompra() {
        return campoValorCompra;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
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
    
    
    public TelaEstoque(SistemaMercado sistema){
        super(sistema);
        setSize(800, 400); 
        setLayout(null);
        setLocationRelativeTo(null);
        
        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();
        JPanel painelBotoes = new JPanel();
        
        painelTextos.setLayout(new GridLayout(4, 1, 0, 10));
        painelCampos.setLayout(new GridLayout(4, 1, 0, 10));
        painelBotoes.setLayout(new GridLayout(1, 2, 20, 10));
        
        painelTextos.setBounds(90, 100, 160, 160);
        painelCampos.setBounds(250, 100, 240, 160);
        painelBotoes.setBounds(250, 280, 240, 50);
        
        JLabel nome = new JLabel("Nome:");
        JLabel codigo = new JLabel("Codigo:");
        JLabel unidade = new JLabel("Unidades:");
        JLabel valorCompra = new JLabel("Valor de compra:");
        
        campoNome = new JTextField(1);
        campoCodigo = new JTextField(1);
        campoNome.setEnabled(false);
        campoCodigo.setEnabled(false);
        campoUnidade = new JTextField(1);
        campoValorCompra = new JTextField(1);
        
        setBotaoConfirmar(new JButton("Cadastrar"));
        setBotaoCancelar(new JButton("Cancelar"));

        JComponent[] Textos = {nome, codigo, unidade, valorCompra};
        JTextField [] Campos = {campoNome, campoCodigo,campoUnidade, campoValorCompra};
        JComponent [] Botoes = {getBotaoConfirmar(), getBotaoCancelar()};
        
        setFonteDoBotao(new Font("arial",Font.BOLD, 15));
        adicionarFontes(Botoes);
        adicionarAoPainel(Textos, painelTextos);
        adicionarAoPainel(Campos, painelCampos);
        adicionarAoPainel(Botoes, painelBotoes);
        adicionarFontes(Textos);
        adicionarFontes(Campos);

        
        setCampos(Campos);
        add(painelTextos);
        add(painelCampos);
        add(painelBotoes);
        
        OuvinteDoEstoque ouvinteEstoque = new OuvinteDoEstoque(this, sistema);
        getBotaoConfirmar().addActionListener(ouvinteEstoque);
        getBotaoCancelar().addActionListener(ouvinteEstoque);
        setVisible(true);
    }

    public void receberProduto(Produto produto){
        this.produto = produto;
        this.campoCodigo.setText(produto.getCodigo());
        this.campoNome.setText(produto.getNome());
        repaint();
    }

    public class OuvinteDoEstoque extends OuvinteDeVerificacao{
        private TelaEstoque tela;

        public OuvinteDoEstoque(TelaEstoque tela, SistemaMercado sistema){
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
                int unidades = Integer.parseInt(campoUnidade.getText());
                float valor = Float.parseFloat(campoValorCompra.getText());

                produto.setUnidade(produto.getUnidade() + unidades);
                produto.setValorUnitarioDeCompra(valor);
         
                DefaultTableModel modelo = (DefaultTableModel) getTelaTabela().getTabela().getModel();
                modelo.setValueAt(produto.getUnidade(), getLinha(), 2);
                modelo.setValueAt(valor, getLinha(), 3);
                telaTabela.getTabela().repaint();
                JOptionPane.showMessageDialog(tela,"Unidade e valor cadastrado!");

                
                Salvar registro = new Salvar(produto.getCodigo(),  produto.getNome(), unidades, valor, "");
                getSistema().getRegistrosDeCompra().add(registro);
                tela.dispose();

            }catch(Exception error){

            }
        }

        public TelaEstoque getTela() {
            return tela;
        }

        public void setTela(TelaEstoque tela) {
            this.tela = tela;
        }
        
    }
        
    
}
