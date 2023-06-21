//classe representado um único 'todo'

package up.edu.br.entidades;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nome;

    //ADICIONAR RELAÇÕES ENTRE ENTIDADES. EX:
    // relaciona tabela usuário com a tabela listas no bd. Ex(cliente + vendas + produtos = itemvendas)
    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
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
