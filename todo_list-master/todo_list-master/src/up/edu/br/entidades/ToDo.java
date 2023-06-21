package up.edu.br.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ToDo {
    private String titulo;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String conteudo;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "task", fetch = FetchType.LAZY)
    private List<Task> tarefas = new ArrayList<Task>();

    public ToDo(String titulo, int id, String conteudo, Date tempo){
        this.id = id;
        this.conteudo = conteudo;
        this.titulo = titulo;
    }

    public ToDo(){}


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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }


    public List<Task> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Task> tarefas) {
        this.tarefas = tarefas;
    }
}
