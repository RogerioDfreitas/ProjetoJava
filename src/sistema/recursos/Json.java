package sistema.recursos;

import java.io.FileReader;
import java.io.FileWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import sistema.SistemaMercado;

public class Json {
    private ObjectMapper transformador;

    public Json(){
        transformador = new ObjectMapper();
        setTransformador(transformador);

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType("sistema")
            .allowIfSubType("java.util.ArrayList")
            .build();
        getTransformador().activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
    }
    
    public void criarJson(SistemaMercado sistemaDoMercado){
        String filename = "sistemaDoMercadinho.json";
        try {
            FileWriter escritor = new FileWriter(filename);
            String jsonSistemaDoMercado = transformador.writeValueAsString(sistemaDoMercado);
            escritor.write(jsonSistemaDoMercado);
            escritor.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public SistemaMercado lerJson(){
        SistemaMercado sistemaDoMercado;
        String filename = "sistemaDoMercadinho.json";
        try {
            FileReader leitor = new FileReader(filename);
            sistemaDoMercado = transformador.readValue(leitor, SistemaMercado.class);
            leitor.close();
        } catch (Exception e) {
            sistemaDoMercado = new SistemaMercado();
            System.out.println(e.getMessage());
        }
        return sistemaDoMercado;
    }


    public ObjectMapper getTransformador() {
        return transformador;
    }

    public void setTransformador(ObjectMapper transformador) {
        this.transformador = transformador;
    }
}