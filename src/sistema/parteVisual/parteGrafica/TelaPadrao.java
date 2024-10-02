package sistema.parteVisual.parteGrafica;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import java.text.ParseException;

import sistema.SistemaMercado;
import java.awt.*;

public class TelaPadrao extends JFrame{
    private SistemaMercado sistema;
    protected JButton botaoConfirmar;
    protected JButton botaoCancelar;
    private JTextField[] campos;
    private Font fonteDoTitulo = new Font("arial", Font.PLAIN, 30);
    private Font fonteDoCampo = new Font("arial", Font.ITALIC, 15);
    private Font fonteDoBotao = new Font("arial", Font.ITALIC, 15);

    public TelaPadrao(SistemaMercado sistema){
        setSistema(sistema);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public JFormattedTextField criarCampoFormatado(String formatoMascara, char placeHolder){
        try {
            MaskFormatter mascara = new MaskFormatter(formatoMascara);
            if(placeHolder != ' '){
                mascara.setPlaceholderCharacter(placeHolder);
            }

            return new JFormattedTextField(mascara);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void adicionarTitulo(String texto){
            int largura = getWidth();
            JLabel titulo = new JLabel(texto, JLabel.CENTER);
            titulo.setFont(getFonteDoTitulo());
            
            titulo.setBackground(Color.WHITE);
            titulo.setOpaque(true);
            add(titulo);
    }

    public void adicionarFontes(JComponent[]  componentes){
        for(JComponent componente : componentes){
            if(componente instanceof JTextField || componente instanceof JLabel || componente instanceof JPasswordField){
                componente.setFont(getFonteDoCampo());
            }else if(componente instanceof JButton){
                componente.setFont(getFonteDoBotao());
            }
        }
    }
    
    public void adicionarAoPainel(JComponent[] componentes, JPanel painel){
        for(JComponent componente : componentes){
            painel.add(componente);
        }
    }

    public SistemaMercado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaMercado sistema) {
        this.sistema = sistema;
    }

    public Font getFonteDoCampo() {
        return fonteDoCampo;
    }

    public Font getFonteDoBotao() {
        return fonteDoBotao;
    }
    

    public void setFonteDotitulo(Font fonteDoTitulo) {
        this.fonteDoTitulo = fonteDoTitulo;
    }

    public void setFonteDoCampo(Font fonteDoCampo) {
        this.fonteDoCampo = fonteDoCampo;
    }

    public void setFonteDoBotao(Font fonteDoBotao) {
        this.fonteDoBotao = fonteDoBotao;
    }

    public Font getFonteDoTitulo() {
        return fonteDoTitulo;
    }

    public JButton getBotaoConfirmar() {
        return botaoConfirmar;
    }

    public void setBotaoConfirmar(JButton botaoConfirmar) {
        this.botaoConfirmar = botaoConfirmar;
    }

    public JButton getBotaoCancelar() {
        return botaoCancelar;
    }

    public void setBotaoCancelar(JButton botaoCancelar) {
        this.botaoCancelar = botaoCancelar;
    }

    public JTextField[] getCampos() {
        return campos;
    }

    public void setCampos(JTextField[] campos) {
        this.campos = campos;
    }
}