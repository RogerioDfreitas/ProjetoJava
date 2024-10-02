package sistema.parteVisual.parteGrafica;

import javax.swing.*;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteUsuario;
import sistema.usuarios.funcionarios.Usuario;

import java.awt.*;


public class TelaUsuario extends JFrame{
    private Font fonteDoBotao = new Font("arial", Font.ITALIC, 15);
    private Font fonteTitulo = new Font("arial", Font.ITALIC, 25);
    private JPanel painelBotoes;
    private JButton cadastrarCliente;
    private JMenuItem trocarUsuario;
    private JMenuItem sair;
    protected SistemaMercado sistema;
    private JLabel titulo;

    public JButton getCadastrarCliente() {
        return cadastrarCliente;
    } 
    
    public void setCadastrarCliente(JButton cadastrarCliente) {
        this.cadastrarCliente = cadastrarCliente;
    }
    
    public Font getFonteDoBotao() {
        return fonteDoBotao;
    }
    
    public void setFonteDoBotao(Font fonteDoBotao) {
        this.fonteDoBotao = fonteDoBotao;}
    

    public Font getFonteTitulo() {
        return fonteTitulo;
    }

    public void setFonteTitulo(Font fonteTitulo) {
        this.fonteTitulo = fonteTitulo;
    }

    public JPanel getPainelBotoes() {
        return painelBotoes;
    }

    public void setPainelBotoes(JPanel painelBotoes) {
        this.painelBotoes = painelBotoes;
    }

    public SistemaMercado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaMercado sistema) {
        this.sistema = sistema;
    }

    public JMenuItem getTrocarUsuario() {
        return trocarUsuario;
    }
    
    public void setTrocarUsuario(JMenuItem trocarUsuario) {
        this.trocarUsuario = trocarUsuario;
    }
    
    public JMenuItem getSair() {
        return sair;
    }
    
    public void setSair(JMenuItem sair) {
        this.sair = sair;
    }

    public JLabel getTitulo() {
        return titulo;
    }

    public void setTitulo(JLabel titulo) {
        this.titulo = titulo;
    }

    public TelaUsuario(SistemaMercado sistema, Usuario usuario){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSistema(sistema);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLUE);
        painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.BLUE);
        cadastrarCliente = adicionarBotao("Registrar Cliente", getFonteDoBotao(), painelBotoes,null);
        

        adicionarMenu();

        OuvinteUsuario ouvinteUsuario = new OuvinteUsuario(this, sistema);
        getCadastrarCliente().addActionListener(ouvinteUsuario);
        getTrocarUsuario().addActionListener(ouvinteUsuario);
        getSair().addActionListener(ouvinteUsuario);

        add(painelBotoes);
    }

    public JLabel adicionarTitulo (String texto){
        JLabel titulo = new JLabel(texto, JLabel.CENTER);
        titulo.setOpaque(true);
        titulo.setFont(fonteTitulo);
        titulo.setBounds(0, 0, getWidth(), getHeight()/10);
        titulo.setBackground(Color.WHITE);
        titulo.setForeground(Color.BLUE);
        return titulo;
    }
    public JButton adicionarBotao(String texto, Font font, JPanel painel, ImageIcon icone){
        JButton botao = new JButton(texto);
        if(icone != null){
            botao.setVerticalTextPosition(SwingConstants.BOTTOM);
            botao.setHorizontalTextPosition(SwingConstants.CENTER);
            botao.setIcon(icone);
        }
        botao.setFocusable(false);
        botao.setFont(font);
        botao.setBackground(Color.WHITE);
        painel.add(botao);
        return botao;
    }
    
    public JMenuBar adicionarMenu(){
        JMenuBar barraDoMenu = new JMenuBar();
        JMenu opcoes = new JMenu("Configuração");
        trocarUsuario = new JMenuItem("Trocar Usuario");
        sair = new JMenuItem("Sair");
        opcoes.add(trocarUsuario);
        opcoes.add(sair);
        barraDoMenu.add(opcoes);
        barraDoMenu.add(Box.createHorizontalGlue());
        setJMenuBar(barraDoMenu);
        barraDoMenu.add(opcoes);

        setJMenuBar(barraDoMenu);
        return barraDoMenu;
       
    } 

    public int calcularX(int larguraPainel){
        int x = (getWidth() - larguraPainel)/2;
        return x;
    }

    public int calcularY(JLabel titulo){
        int y = titulo.getHeight() + 50;
        return y;
    }

    public int Altura(int ladoDoBotao, int gapV, int QTDlinhas){
        int altura = (QTDlinhas * ladoDoBotao) + (QTDlinhas - 1) * gapV;
        return altura;
    }
    public int Largura(int ladoDoBotao, int gapH, int QTDcolunas){
        int largura = (QTDcolunas * ladoDoBotao) + (QTDcolunas - 1) * gapH;
        return largura;
    }
}
