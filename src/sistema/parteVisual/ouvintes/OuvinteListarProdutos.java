package sistema.parteVisual.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sistema.SistemaMercado;
import sistema.parteVisual.parteGrafica.TelaEditar;
import sistema.parteVisual.parteGrafica.TelaEstoque;
import sistema.parteVisual.parteGrafica.TelaListarProdutos;
import sistema.recursos.Json;
import sistema.recursos.Produto;
import sistema.recursos.Salvar;

public class OuvinteListarProdutos implements ActionListener {
    private SistemaMercado sistema;
    private TelaListarProdutos tela;

    public OuvinteListarProdutos(TelaListarProdutos tela, SistemaMercado sistema) {
        this.tela = tela;
        this.sistema = sistema;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int linha = tela.getTabela().getSelectedRow();
        Json json = new Json();

        if (linha == -1) {
            JOptionPane.showMessageDialog(tela, "Nenhum Produto Selecionado ", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<Produto> listaProdutos = sistema.getProdutosEmEstoque();
            Produto produto = listaProdutos.get(linha);

            switch (e.getActionCommand()) {
                case "Detalhes":
                    mostrarDetalhesProduto(produto);
                    break;
                case "Editar":
                    editarProduto(produto, linha);
                    break;
                case "Excluir":
                    excluirProduto(produto, listaProdutos, linha);
                    break;
                case "Valor unitário de venda":
                    mudarValorDeVenda(produto, linha);
                    break;
                case "Registrar Entrada":
                    registrarEntradaProduto(produto, linha);
                    break;
                default:
                    break;
            }
            json.criarJson(sistema);
        }
    }

    private void mostrarDetalhesProduto(Produto produto) {
        JOptionPane.showMessageDialog(tela, "Detalhes:\n\nCodigo: " + produto.getCodigo() + "\nNome: " + produto.getNome() + "\nUnidades: " + produto.getUnidade() + "\nValor de compra: " + produto.getValorUnitarioDeCompra() + "\nValor de venda: " + produto.getValorUnitarioDeVenda());
    }

    private void editarProduto(Produto produto, int linha) {
        TelaEditar telaEditar = new TelaEditar(sistema);
        telaEditar.receberProduto(produto);
        telaEditar.setTelaTabela(this.tela);
        telaEditar.setLinha(linha);
    }

    private void excluirProduto(Produto produto, ArrayList<Produto> listaProdutos, int linha) {
        if (verificarVendaProduto(produto, sistema.getRegistrosDeVenda())) {
            excluirProdutoDaLista(produto, listaProdutos, linha);
        } else {
            JOptionPane.showMessageDialog(tela, "Produto já foi vendido");
        }
    }

    private void excluirProdutoDaLista(Produto produto, ArrayList<Produto> listaProdutos, int linha) {
        int resposta = JOptionPane.showConfirmDialog(tela, "Você deseja excluir " + produto.getNome() + "?");
        if (resposta == JOptionPane.YES_OPTION) {
            listaProdutos.remove(produto);
            DefaultTableModel modelo = (DefaultTableModel) tela.getTabela().getModel();
            modelo.removeRow(linha);
            tela.getTabela().repaint();
        }
    }

    private boolean verificarVendaProduto(Produto produto, ArrayList<Salvar> registrosDeVenda) {
        return registrosDeVenda.stream().noneMatch(registro -> registro.getNome().equals(produto.getNome()));
    }

    private void mudarValorDeVenda(Produto produto, int linhaSelecionada) {
        String input = JOptionPane.showInputDialog(tela, "Digite um valor:");
        try {
            float valor = Float.parseFloat(input);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(tela, "Valor não pode ser zero");
            } else {
                produto.setValorUnitarioDeVenda(valor);
                DefaultTableModel modelo = (DefaultTableModel) tela.getTabela().getModel();
                modelo.setValueAt(valor, linhaSelecionada, 4);
                tela.getTabela().repaint();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(tela, "O valor precisa ser numérico!");
        }
    }

    private void registrarEntradaProduto(Produto produto, int linhaSelecionada) {
        TelaEstoque telaEstoque = new TelaEstoque(sistema);
        telaEstoque.receberProduto(produto);
        telaEstoque.setTelaTabela(this.tela);
        telaEstoque.setLinha(linhaSelecionada);
    }
}
       