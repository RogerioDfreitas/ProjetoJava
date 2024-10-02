package sistema.parteVisual.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sistema.SistemaMercado;
import sistema.parteVisual.parteGrafica.TelaCadastrarCliente;
import sistema.parteVisual.parteGrafica.TelaDeVenda;
import sistema.recursos.Cupom;
import sistema.recursos.Pdf;
import sistema.recursos.Produto;
import sistema.recursos.Salvar;
import sistema.usuarios.funcionarios.Cliente;

public class OuvinteVendas extends OuvinteDeVerificacao {
    private TelaDeVenda tela;
    private SistemaMercado sistema;

    public OuvinteVendas(TelaDeVenda tela, SistemaMercado sistema) {
        super(tela, sistema);
        this.tela = tela;
        this.sistema = sistema;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(tela.getBotaoVender())) {
            venderProduto();
        }
        super.actionPerformed(e);
    }

    private void venderProduto() {
        JTextField campoCupom = tela.getCampoCupom();
        String codigoCupom = campoCupom.getText();
        Cupom cupom = sistema.validarCupom(codigoCupom);
        float total = tela.getTotalDeVendas();
        float totalComDesconto = 0;

        if (!codigoCupom.equals("_____") && cupom != null) {
            float desconto = cupom.getDesconto();
            totalComDesconto = total - (total * desconto);
            tela.setTotalDeVendas(totalComDesconto);
            JOptionPane.showMessageDialog(tela, "Total: " + total + "\nTotal com desconto: " + totalComDesconto);
        }

        JOptionPane.showMessageDialog(tela, "Total: " + total);
        tela.dispose();
        validarCPF();

        for (Produto produto : tela.getCestinha()) {
            if (totalComDesconto != 0) {
                total = totalComDesconto;
            }

            Salvar registro = new Salvar(produto.getCodigo(), produto.getNome(), produto.getUnidade(), produto.getValorUnitarioDeVenda(), "");
            sistema.getRegistrosDeVenda().add(registro);
        }

        String CPF = tela.getCampoCPF().getText();
        Cliente cliente = sistema.buscar(CPF);
        Pdf pdf = new Pdf();
        pdf.emitirNotinha(sistema, cliente, CPF, tela.getCestinha());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(tela.getCampoQTD()) && !Character.isDigit(e.getKeyChar())) {
            e.consume();
        }
    }

    @Override
    protected void confirmar() {
        JTextField campoDoCodigo = tela.getCampoCodigo();
        String codigo = campoDoCodigo.getText();
        Produto produto = sistema.buscarCodigo(codigo);

        int quantidade = Integer.parseInt(tela.getCampoQTD().getText());
        validarCPF();

        if (produto == null) {
            JOptionPane.showMessageDialog(tela, "Produto não encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else if (quantidade > produto.getUnidade()) {
            JOptionPane.showMessageDialog(tela, "No momento apenas " + produto.getUnidade() + " no estoque", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            produto.setUnidade(produto.getUnidade() - quantidade);

            float valorUnitario = produto.getValorUnitarioDeVenda();
            float total = tela.getTotalDeVendas();
            tela.setTotalDeVendas(total + (quantidade * valorUnitario));
            Produto produtoComprado = new Produto(codigo, sistema);

            produtoComprado.setCodigo(produto.getCodigo());
            produtoComprado.setNome(produto.getNome());
            produtoComprado.setUnidade(quantidade);
            produtoComprado.setValorUnitarioDeVenda(produto.getValorUnitarioDeVenda());
            produtoComprado.setValorUnitarioDeCompra(produto.getValorUnitarioDeCompra());

            tela.getCestinha().add(produtoComprado);
            tela.getBotaoVender().setEnabled(true);
            JOptionPane.showMessageDialog(tela, "Produto adicionado na Cestinha!");
        }

        tela.getCampoCodigo().setText("");
        tela.getCampoQTD().setText("");
        tela.getCampoCPF().setEnabled(false);
        tela.repaint();
    }

    private void validarCPF() {
        JTextField campoDoCPF = tela.getCampoCPF();
        String CPF = campoDoCPF.getText();

        Cliente cliente = sistema.buscar(CPF);

        if (cliente == null) {
            int resposta = JOptionPane.showConfirmDialog(tela, "Deseja cadastrar o cliente?", "Cliente não cadastrado", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                TelaCadastrarCliente telaCadastroCliente = new TelaCadastrarCliente(sistema);
            }
        }
    }
}