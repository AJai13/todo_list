package up.edu.br.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nome;

     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<ToDo> listas = new ArrayList<ToDo>();
     public User() {}

    public User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() { return nome; }

    public void setNome(String user) { this.nome = user; }


    @Override
    public String toString() {
        return "[" + id + "] " + " " + nome + " ";
    }
}
