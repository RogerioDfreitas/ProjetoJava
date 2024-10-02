package sistema.parteVisual.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.SistemaMercado;
import sistema.parteVisual.parteGrafica.TelaCadastrarCliente;
import sistema.parteVisual.parteGrafica.TelaLogin;
import sistema.parteVisual.parteGrafica.TelaUsuario;

public class OuvinteUsuario implements ActionListener{
    private TelaUsuario tela;
    private SistemaMercado sistema;

    public TelaUsuario getTela() {
        return tela;
    }
    
    public void setTela(TelaUsuario tela) {
        this.tela = tela;
    }
        
    public SistemaMercado getSistema() {
        return sistema;
    }
    
    public void setSistema(SistemaMercado sistema) {
        this.sistema = sistema;
    }
    
    public OuvinteUsuario(TelaUsuario tela, SistemaMercado sistema){
        setTela(tela);
        setSistema(sistema);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(tela.getCadastrarCliente())){
            TelaCadastrarCliente tela = new TelaCadastrarCliente(sistema);
        }
        else if(e.getSource().equals(tela.getTrocarUsuario())){
            tela.dispose();
            TelaLogin tela = new TelaLogin(sistema);
        }
        else if(e.getSource().equals(tela.getSair())){
            tela.dispose();
        }
    } 
        
}
