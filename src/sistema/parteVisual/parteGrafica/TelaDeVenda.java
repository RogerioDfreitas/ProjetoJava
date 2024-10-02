package sistema.parteVisual.parteGrafica;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteVendas;
import sistema.recursos.Produto;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class TelaDeVenda extends TelaPadrao{

    private JTextField campoCPF;
    private JTextField campoCodigo;
    private JTextField campoQTD;
    private JTextField campoCupom;
    private JButton botaoVender;
    private ArrayList<Produto> cestinha = new ArrayList<>();
    private float totalDeVendas;

    public JTextField getCampoCupom() {
        return campoCupom;
    }

    public void setCampoCupom(JTextField campoCupom) {
        this.campoCupom = campoCupom;
    }

    public JTextField getCampoCPF() {
        return campoCPF;
    }

    public void setCampoCPF(JTextField campoCPF) {
        this.campoCPF = campoCPF;
    }

    public JTextField getCampoQTD() {
        return campoQTD;
    }

    public void setCampoQTD(JTextField campoQTD) {
        this.campoQTD = campoQTD;
    }

    public JButton getBotaoVender() {
        return botaoVender;
    }

    public void setBotaoVender(JButton botaoVender) {
        this.botaoVender = botaoVender;
    }

    public ArrayList<Produto> getCestinha() {
        return cestinha;
    }

    public void setCestinha(ArrayList<Produto> cestinha) {
        this.cestinha = cestinha;
    }

    public float getTotalDeVendas() {
        return totalDeVendas;
    }

    public void setTotalDeVendas(float totalDeVendas) {
        this.totalDeVendas = totalDeVendas;
    }

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public void setCampoCodigo(JTextField campoCodigo) {
        this.campoCodigo = campoCodigo;
    }


    public TelaDeVenda(SistemaMercado sistema) {
        super(sistema);
        setSize(650, 440);
        setLocationRelativeTo(null);
        setTotalDeVendas(0);
        JPanel painelTextos = new JPanel();
        JPanel painelCampos = new JPanel();

        

        painelTextos.setLayout(new GridLayout(4, 1, 0, 20));
        painelCampos.setLayout(new GridLayout(4, 1, 0, 20));
        painelTextos.setBounds(40, 100, 200, 180);
        painelCampos.setBounds(240, 100, 370, 180);

        campoCPF = criarCampoFormatado("***.***.***-**", '_');
        campoCodigo = criarCampoFormatado("*****", '_');
        campoQTD = new JTextField();
        campoCupom = criarCampoFormatado("AAAAA", '_');
        
        JLabel textoCPF = new JLabel("CPF Cliente");
        JLabel textoProduto = new JLabel("Código do Produto: ");
        JLabel textoQTD = new JLabel("Unidades: ");
        JLabel textoCupom = new JLabel("Cupom: ");

        setBotaoConfirmar(new JButton("Adicionar na Cestinha"));
        botaoVender = new JButton("Finalizar");
        botaoVender.setEnabled(false);
        getBotaoConfirmar().setBounds(240, 320, 200, 50);
        botaoVender.setBounds(490, 320, 120, 50);

        JTextField[] Campos = {campoCPF, campoCodigo, campoQTD, campoCupom};

        JComponent[] Textos = { textoCPF, textoProduto, textoQTD, textoCupom};

        JComponent[] Botoes = {getBotaoConfirmar(), getBotaoVender()};
        
        setCampos(Campos);
        adicionarFontes(Textos);
        adicionarFontes(Campos);
        setFonteDoBotao(new Font("arial", Font.BOLD, 15));
        adicionarFontes(Botoes);
        adicionarAoPainel(Textos, painelTextos);
        adicionarAoPainel(Campos, painelCampos);

        OuvinteVendas ouvinteVendas = new OuvinteVendas(this,sistema);
        getCampoCPF().addKeyListener(ouvinteVendas);
        getCampoCodigo().addKeyListener(ouvinteVendas);
        getCampoQTD().addKeyListener(ouvinteVendas);
        getCampoCupom().addKeyListener(ouvinteVendas);
        getBotaoConfirmar().addActionListener(ouvinteVendas);
        getBotaoVender().addActionListener(ouvinteVendas);
        
        add(getBotaoConfirmar());
        add(botaoVender);
        add(painelTextos);
        add(painelCampos);
        setVisible(true);
    }



}
