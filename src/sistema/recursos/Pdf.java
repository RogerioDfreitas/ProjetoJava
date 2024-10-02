package sistema.recursos;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sistema.SistemaMercado;
import sistema.usuarios.funcionarios.Cliente;

public class Pdf {

    public void gerarBalancoMensal(SistemaMercado sistema){
        try {
            Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
            PdfWriter.getInstance(doc, new FileOutputStream("Balanço_Mensal.pdf"));
            doc.open();

            Font fonte = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph cabecalho = new Paragraph("Balanço Mensal", fonte); 
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(40);
        
            PdfPTable tabelaVendas = criarTabela(5, "Vendas Realizadas no Mês");
            PdfPTable tabelaCompras  = criarTabela(5, "Compras Realizadas no Mês");
            PdfPTable tabelaBalanco = criarTabela(3, "Balanço Mensal");
            String[] Produtos = {"Codigo", "Unidades", "Nome", "Valor Unitario.", "Total"};
            String[] Balanco = {"Valor Total da Compra", "Total vendido","Total apurado"};

            float totalComprado = sistema.TotalDeCompras();
            float totalVendido = sistema.TotalDeVendas();
            float totalApurado = sistema.TotalApurado();
            
            addCabecalhosColunas(Produtos, tabelaVendas);
            addCabecalhosColunas(Produtos, tabelaCompras);
            addCabecalhosColunas(Balanco, tabelaBalanco);
            addLinha(sistema.getRegistrosDeVenda(), tabelaVendas);
            addLinha(sistema.getRegistrosDeCompra(), tabelaCompras);

            tabelaBalanco.addCell(String.valueOf(totalComprado));
            tabelaBalanco.addCell(String.valueOf(totalVendido));
            tabelaBalanco.addCell(String.valueOf(totalApurado));
            
            doc.add(cabecalho);
            doc.add(tabelaCompras);
            doc.add(tabelaVendas);
            doc.add(tabelaBalanco);
            doc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emitirNotinha(SistemaMercado sistema, Cliente cliente, String CPF, ArrayList<Produto> cestinha){
        try {
            Document notinha = new Document();
            String caminhoNotinha = "src/notinha/"+ CPF +"Notinhas.pdf";
            PdfWriter.getInstance(notinha, new FileOutputStream(caminhoNotinha));
            notinha.open();

            Font fonte = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph cabecalho = new Paragraph("Nota fiscal", fonte); 
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(40);
            
            String nomeCliente;

            if(cliente == null){
                nomeCliente = "Cliente não identificado";
            }else{
                nomeCliente = cliente.getNome();
            }
            
            PdfPTable tabelaInfo = criarTabela(2, "Informações usuário");
            PdfPTable tabelaNotinha = criarTabela(5, "Nota Fiscal");
            String[] cabecalhosNotinha = {"Nome", "Unidades", "Codigo","Valor Unit.", "Total em Item"};
            
           
            tabelaInfo.addCell("Nome");
            tabelaInfo.addCell(nomeCliente);
            tabelaInfo.addCell("CPF");
            tabelaInfo.addCell(CPF);

            addCabecalhosColunas(cabecalhosNotinha, tabelaNotinha);

            float totalDeCompras = 0;
            float valorTotalNoProduto = 0;

            for (Produto produto : cestinha) {
                valorTotalNoProduto  = produto.getUnidade() * produto.getValorUnitarioDeVenda();
                totalDeCompras += valorTotalNoProduto;
                tabelaNotinha.addCell(produto.getNome());
                tabelaNotinha.addCell(String.valueOf(produto.getUnidade()));
                tabelaNotinha.addCell(produto.getCodigo());
                tabelaNotinha.addCell(String.valueOf(produto.getValorUnitarioDeVenda()));
                tabelaNotinha.addCell(String.valueOf(valorTotalNoProduto));
            }
            
            Paragraph p = new Paragraph("Total: ");
            PdfPCell cabecalhoTotal = new PdfPCell(p);
            cabecalhoTotal.setColspan(4);
            tabelaNotinha.addCell(cabecalhoTotal);
            tabelaNotinha.addCell(String.valueOf(totalDeCompras));

            notinha.add(cabecalho);
            notinha.add(tabelaInfo);
            notinha.add(tabelaNotinha);
            notinha.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public PdfPTable criarTabela(int colunas, String cabecalho){            
        PdfPTable tabela = new PdfPTable(colunas);
        Paragraph p = new Paragraph(cabecalho);
        p.setAlignment(Element.ALIGN_CENTER);
        PdfPCell cabecalhoCell = new PdfPCell(p);
        cabecalhoCell.setColspan(colunas);
        cabecalhoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabela.addCell(cabecalhoCell);
        tabela.setSpacingAfter(50);
        return tabela;
    }

    public void addCabecalhosColunas(String[] cabecalho, PdfPTable tabela) throws Exception{
        if(tabela.getNumberOfColumns() != cabecalho.length){
            throw new Exception("ERRO: Número de títulos diferente do número de colunas");
        }
        for(String titulo : cabecalho){
            tabela.addCell(titulo);
        }
    }
    
    public void addLinha(ArrayList<? extends Salvar> registros, PdfPTable tabela){
        for(Salvar registro : registros){
            tabela.addCell(registro.getCodigo());
            tabela.addCell(String.valueOf(registro.getUnidades()));
            tabela.addCell(registro.getNome());
            tabela.addCell(String.valueOf(registro.getValor()));
            tabela.addCell(String.valueOf(registro.getUnidades() * registro.getValor()));
        }
    }
}
    
