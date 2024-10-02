package sistema.parteVisual.parteGrafica;


import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import sistema.SistemaMercado;
import sistema.parteVisual.ouvintes.OuvinteFuncionario;
import sistema.recursos.Pdf;
import sistema.usuarios.funcionarios.Usuario;

public class TelaGerente extends TelaFuncionario {

    private JButton cadastrarUsuario;
    private JButton enviarCupons;
    private JButton gerarBalancoMensal;

    public JButton getCadastrarUsuario() {
        return cadastrarUsuario;
    }

    public void setCadastrarUsuario(JButton cadastrarUsuario) {
        this.cadastrarUsuario = cadastrarUsuario;
    }

    public JButton getGerarBalancoMensal() {
        return gerarBalancoMensal;
    }

    public void setGerarBalancoMensal(JButton gerarBalancoMensal) {
        this.gerarBalancoMensal = gerarBalancoMensal;
    }

    public JButton getEnviarCupons() {
        return enviarCupons;
    }

    public void setEnviarCupons(JButton enviarCupons) {
        this.enviarCupons = enviarCupons;
    }

    public TelaGerente(SistemaMercado sistema, Usuario usuario){
        super(sistema, usuario);
        setSize(720, 600);
        setLocationRelativeTo(null);

        setTitulo(adicionarTitulo("Seja bem-vindo(a) "  + usuario.getNome()));

        int larguraJanela = Largura(250, 10, 3);
        int alturaJanela = Altura(150, 20, 2);
        int x = calcularX(larguraJanela);
        int y = calcularY(getTitulo());


        cadastrarUsuario = adicionarBotao("Registrar funcionario", getFonteDoBotao(), getPainelBotoes(), null);
        gerarBalancoMensal = adicionarBotao("Balanço mensal", getFonteDoBotao(), getPainelBotoes(), null);
        enviarCupons = adicionarBotao("Criar cupons ", getFonteDoBotao(), getPainelBotoes(),null);
        
        OuvinteGerente ouvinteGerente = new OuvinteGerente(this, sistema, usuario);
        cadastrarUsuario.addActionListener(ouvinteGerente);
        enviarCupons.addActionListener(ouvinteGerente);
        gerarBalancoMensal.addActionListener(ouvinteGerente);

        getPainelBotoes().setLayout(new GridLayout(2,3, 20, 20));
        getPainelBotoes().setBounds(x, y, larguraJanela, alturaJanela);
        add(getTitulo());
    }
    public class OuvinteGerente extends OuvinteFuncionario {
        public OuvinteGerente(TelaFuncionario tela, SistemaMercado sistema, Usuario usuario) {
            super(tela, sistema, usuario);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            JButton botao = (JButton) e.getSource();

            if(botao.equals(cadastrarUsuario)){
                TelaCadastrarUsuario telaDeCadastroDeUsuario = new TelaCadastrarUsuario(getSistema());
            }else if (botao.equals(enviarCupons)){
                TelaDoCupon telaCupom = new TelaDoCupon(getSistema());
            }else{
                Pdf pdf = new Pdf();
                pdf.gerarBalancoMensal(getSistema());
                JOptionPane.showMessageDialog(getTela(), "PDF foi gerado.");
            }
        }
    }

    
}
