package sistema.parteVisual.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sistema.SistemaMercado;
import sistema.parteVisual.parteGrafica.TelaPadrao;
import sistema.recursos.Json;

public abstract class  OuvinteDeVerificacao implements ActionListener, KeyListener {
    SistemaMercado sistema;
    TelaPadrao tela;

    public SistemaMercado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaMercado sistema) {
        this.sistema = sistema;
    }

    public TelaPadrao getTela() {
        return tela;
    }
    
    public void setTela(TelaPadrao tela) {
        this.tela = tela;
    }

    public OuvinteDeVerificacao(TelaPadrao tela, SistemaMercado sistema){
        setTela(tela);
        setSistema(sistema);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Json json = new Json();
        if(source.equals(tela.getBotaoConfirmar())){
            boolean cadastroValido = this.validarCampo();
            if(cadastroValido){
                confirmar();
            }
        }else if(source.equals(tela.getBotaoCancelar())){
            tela.dispose();
        }
        json.criarJson(sistema);
    }

    protected abstract void confirmar(); 

    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public boolean validarCampo(){
        String texto;
        for(JTextField campo : tela.getCampos()){
            if (campo instanceof JPasswordField){
                JPasswordField campoSenha = (JPasswordField) campo;
                texto = new String(campoSenha.getPassword());

            }else{
                texto = campo.getText();
            }
            
            if(texto.trim().isEmpty() && campo.isEnabled()){

                JOptionPane.showMessageDialog(tela, "Preencha todos os campos","Campo Vazio", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    

}
