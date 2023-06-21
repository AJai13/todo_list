package up.edu.br.front;

import up.edu.br.entidades.User;
import up.edu.br.persistencia.UserPersistencia;

import java.util.List;

public class Utilidade {
    public static void exibirUsuarios(List<User> usuarios){
        int contador = 0;
        if (!usuarios.isEmpty()) {
            System.out.println("========================");
            System.out.println("Usuários: ");
            for (User x : usuarios) {
                System.out.println(contador + ". " + x.getNome());
                contador++;
            }
            System.out.println("========================");}
    }
}
