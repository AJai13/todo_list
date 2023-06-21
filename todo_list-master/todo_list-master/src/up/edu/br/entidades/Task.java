package up.edu.br.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Task {
    private String titulo;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private String conteudo;

    private Date tempo;

    public Task(String titulo, int id, boolean status, String conteudo, Date tempo) {
        this.titulo = titulo;
        this.id = id;
        this.status = status;
        this.conteudo = conteudo;
        this.tempo = tempo;
    }

    public Task() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }
}
