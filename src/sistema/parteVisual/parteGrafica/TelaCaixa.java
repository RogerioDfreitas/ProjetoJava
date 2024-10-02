package sistema.parteVisual.parteGrafica;

import javax.swing.*;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteUsuario;
import sistema.usuarios.funcionarios.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaCaixa extends TelaUsuario{
    
    private TelaCaixa tela;
    private SistemaMercado sistema;
    private JButton realizarVenda;

    public JButton getRealizarVenda() {
        return realizarVenda;
    }
            
    public void setRealizarVenda(JButton realizarVenda) {
        this.realizarVenda = realizarVenda;
    }

    public TelaCaixa getTela() {
        return tela;
    }
    public void setTela(TelaCaixa tela) {
        this.tela = tela;
    }
            
    public SistemaMercado getSistema() {
        return sistema;
    }
        
    public void setSistema(SistemaMercado sistema) {
        this.sistema = sistema;
    }
    
    public TelaCaixa(SistemaMercado sistema, Usuario usuario){
        super(sistema, usuario);
        setSize(550, 350);
        setLocationRelativeTo(null);

        setTitulo(adicionarTitulo("Bem-vindo(a), " + usuario.getNome()));
        add(getTitulo());

        int larguraPainel = Largura(200,20, 2);
        int alturaPainel = Altura(150, 20, 1);
        int x = calcularX(larguraPainel);
        int y = calcularY(getTitulo());

        realizarVenda = adicionarBotao("Realizar venda", getFonteDoBotao(), getPainelBotoes(),null);

        getPainelBotoes().setLayout(new GridLayout(1,3, 20, 20));
        getPainelBotoes().setBounds(x, y, larguraPainel, alturaPainel);
        OuvinteCaixa ouvinteCaixa = new OuvinteCaixa(this,sistema);
        getRealizarVenda().addActionListener(ouvinteCaixa);
        setVisible(true);
    }

    public class OuvinteCaixa extends OuvinteUsuario {
        private TelaCaixa tela;

        public OuvinteCaixa(TelaCaixa tela, SistemaMercado sistema) {
            super(tela, sistema);
            setTela(tela);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource().equals(realizarVenda)){
                TelaDeVenda TelaDeVendas = new TelaDeVenda(getSistema());
            }
        }

        public TelaCaixa getTela() {
            return tela;
        }

        public void setTela(TelaCaixa tela) {
            this.tela = tela;
        }

    }

    
}
            