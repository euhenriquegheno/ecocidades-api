package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class FeedbackCidadaoModel {
    @Expose(serialize = false)
    private int id;
    @Expose
    private String autor;
    @Expose
    private String tipo;
    @Expose
    private String descricao;
    @Expose
    private String data;
    @Expose
    private String status;
}
