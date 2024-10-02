package sistema.parteVisual.parteGrafica;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteListarProdutos;
import sistema.recursos.Produto;
import sistema.usuarios.funcionarios.Almoxarife;
import sistema.usuarios.funcionarios.Gerente;
import sistema.usuarios.funcionarios.Usuario;

import javax.swing.JPanel;
import java.awt.*;

public class TelaListarProdutos extends JFrame {
    private JTable tabela;
    private SistemaMercado sistema;
    private int linhaSelecionada;
    private Font fonteDoTitulo = new Font("arial", Font.PLAIN, 30);
    private Font fonteDoCampo = new Font("arial", Font.ITALIC, 20);
    private Font fonteDoBotao = new Font("arial", Font.ITALIC, 20);

    public Font getFonteDoTitulo() {
        return fonteDoTitulo;
    }

    public void setFonteDoTitulo(Font fonteDoTitulo) {
        this.fonteDoTitulo = fonteDoTitulo;
    }

    public Font getFonteDoCampo() {
        return fonteDoCampo;
    }

    public void setFonteDoCampo(Font fonteDoCampo) {
        this.fonteDoCampo = fonteDoCampo;
    }

    public Font getFonteDoBotao() {
        return fonteDoBotao;
    }

    public void setFonteDoBotao(Font fonteDoBotao) {
        this.fonteDoBotao = fonteDoBotao;
    }

    public SistemaMercado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaMercado sistema) {
        this.sistema = sistema;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public int getLinhaSelecionada() {
        return linhaSelecionada;
    }

    public void setLinhaSelecionada(int linhaSelecionada) {
        this.linhaSelecionada = linhaSelecionada;
    }


    public TelaListarProdutos(SistemaMercado sistema, Usuario usuario){
        setSize(750, 500);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setSistema(sistema);
        getContentPane().setBackground(Color.BLUE); 
        adicionarTabela();
        String[] botoes = {"Detalhes", "Editar", "Excluir"};
        adicionarBotoes(botoes, usuario);
        
        
    }

    public void adicionarTabela(){
                
        JLabel titulo = new JLabel("Produtos", JLabel.CENTER);
        titulo.setBounds(0, 0, getWidth(), 60);
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        modelo.addColumn("Nome");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Código");
        modelo.addColumn("Valor de Compra");
        modelo.addColumn("Valor de Venda");

        ArrayList<Produto> listaDeProdutos = getSistema().getProdutosEmEstoque();

        for (Produto produto : listaDeProdutos){
            Object[] linha = new Object[5];
            linha[0] = produto.getNome();
            linha[1] = produto.getUnidade();
            linha[2] = produto.getCodigo();
            linha[3] = produto.getValorUnitarioDeCompra();
            linha[4] = produto.getValorUnitarioDeVenda();
            modelo.addRow(linha);  
        }
        
        tabela = new JTable(modelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        JScrollPane painel = new JScrollPane(tabela);
        painel.setBounds(40, 80, 670, 270);
        add(painel);
        titulo.setFont(getFonteDoTitulo());
        titulo.setBackground(Color.WHITE);
        titulo.setForeground(Color.BLACK);
        titulo.setOpaque(true);
        add(titulo);
        setVisible(true);
    }
    
    public void adicionarBotoes(String[] rotulos, Usuario usuario){
        OuvinteListarProdutos ouvinte = new OuvinteListarProdutos(this, sistema);

        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painel.setBounds(getBounds());
        for(String rotulo : rotulos){
            JButton botao = new JButton(rotulo);
            botao.addActionListener(ouvinte);
            botao.setBackground(Color.WHITE); 
            botao.setForeground(Color.BLACK);
            painel.add(botao);
        }
        if(usuario instanceof Gerente){
            JButton botao = new JButton("Valor de venda");
            botao.addActionListener(ouvinte);
            botao.setBackground(Color.WHITE); 
            botao.setForeground(Color.BLACK);
            painel.add(botao);

        }else if (usuario instanceof Almoxarife){
            JButton botao = new JButton("Registrar entrada");
            botao.addActionListener(ouvinte);
            botao.setBackground(Color.WHITE); 
            botao.setForeground(Color.BLACK);
            painel.add(botao);
        }
        
        painel.setBounds(0, 380, getWidth(), 100);
        add(painel);
    }
    
    
    

    
}
