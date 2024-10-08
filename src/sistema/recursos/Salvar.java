package sistema.recursos;

public class Salvar {
    private String codigo;
    private int unidades;
    private String nome;
    private float valor;
    private String data;

    public Salvar(){
        
    }
    
    public Salvar(String codigo, String nome, int unidades, float valor, String data){
        this.codigo = codigo;
        this.nome = nome;
        this.unidades = unidades;
        this.valor = valor;
        this.data = data;
    }

    public Salvar(float valor, String data){
        this.valor = valor;
        this.data = data;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getUnidades() {
        return unidades;
    }
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}