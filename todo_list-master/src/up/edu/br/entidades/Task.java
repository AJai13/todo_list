package up.edu.br.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    private String titulo;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private boolean status;
    private String conteudo;

    @OneToOne
    private ToDo lista;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    public Task(String titulo, int id, boolean status, String conteudo) {
        this.titulo = titulo;
        this.id = id;
        this.status = status;
        this.conteudo = conteudo;
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

    public ToDo getLista() {
        return lista;
    }

    public void setLista(ToDo lista) {
        this.lista = lista;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
