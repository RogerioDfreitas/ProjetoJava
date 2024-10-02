package sistema.parteVisual.parteGrafica;
import javax.swing.*;

import sistema.SistemaMercado;
import sistema.usuarios.funcionarios.Usuario;

import java.awt.*;

public class TelaAlmoxarife extends TelaFuncionario{
    public TelaAlmoxarife(SistemaMercado sistema, Usuario usuario){
        super(sistema, usuario);
        setSize(720, 400);
        setLocationRelativeTo(null);
        setTitulo(adicionarTitulo("Bem-vindo(a), " + usuario.getNome() + "."));
        add(getTitulo());
        int larguraPainel = Largura(200,20, 3);
        int alturaPainel = Altura(150, 20, 1);
        int x = calcularX(larguraPainel);
        int y = calcularY(getTitulo());
        getPainelBotoes().setLayout(new GridLayout(1,3, 20, 20));
        getPainelBotoes().setBounds(x, y, larguraPainel, alturaPainel);
    }
}
    
