package up.edu.br.front;
import up.edu.br.entidades.User;
import up.edu.br.persistencia.UserPersistencia;

import java.lang.ref.PhantomReference;
import java.util.List;

public class UserApp {
    public UserApp() {
        int opc;

        do {
            System.out.println("\n\n");
            System.out.println("***** USUÁRIOS *****");
            System.out.println("1 - Novo usuário    ");
            System.out.println("2 - Alterar usuário ");
            System.out.println("3 - Listar usuários ");
            System.out.println("4 - Excluir usuário ");
            System.out.println("5 - Voltar          ");
            opc = Console.readInt("Opção:    ");

            switch (opc) {
                case 1:
                    incluirUser();
                    break;
                case 2:
                    alterarUser();
                    break;
                case 3:
                    listarUser();
                    break;
                case 4:
                    excluirUser();
                    break;
                case 5:
                    Principal.main(null);
                    break;
            }
        } while (opc != 6);
    }

    //arrumar parâmetros e objs.
    private static void incluirUser() {
        System.out.println("\n\n*** INCLUSÃO DE USUÁRIOS ***");
        User objUser = new User();
            objUser.setNome(Console.readString("Informe o nome do usuário: "));
            if (UserPersistencia.procurarPorId(objUser) == null) {
                UserPersistencia.incluir(objUser);
                System.out.println("\n\nInclusão bem sucedida...");
            } else {
               System.out.println("\n\nUsuário já cadastrado...");
            }
            }


    private static void alterarUser() {
        User objUser = new User();
        objUser.setId(Console.readInt("\n\nInforme o Id do usuário que deseja consultar: "));
        objUser = UserPersistencia.procurarPorId(objUser);
        if(objUser != null) {
            System.out.println("-----------------------");
            System.out.println("ID: " + objUser.getId());
            System.out.println("Nome: " + objUser.getNome());
            System.out.println("-----------------------");
            String resp = Console.readString("\n\nQuer alterar o usuário? (S/ N)");
            if(resp.equals("S")) {
                objUser.setNome(Console.readString("\n\nInforme um novo nome para o usuario: "));
                if(UserPersistencia.alterar(objUser)) {
                    System.out.println("\n\nAlteração realizada...");
                }
                else {
                    System.out.println("\n\nA alteração não pôde ser realizada no momento...");
                }
            }
        }
        else {
            System.out.println("\n\nUsuário não cadastrado..."); }
    }
    private static void listarUser() {
        System.out.println("\n\n*** LISTAGEM DE USUÁRIOS***");
        User objUser = new User();
//        objUser.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
        List<User> users = UserPersistencia.getUsers();
        if (!users.isEmpty()) {
            System.out.println("----------------------------");
            for (User x : users) {
                System.out.println("ID: " + x.getId());
                System.out.println("Nome: " + x.getNome());
                System.out.println("Usuários:");
            }
        } else {
            System.out.println("Usuário(s) não encontrado(s).");
        }
    }

    private static void excluirUser(){
        System.out.println("\n\n**** EXCLUSÃO DE USUÁRIO ****");
        User objUser = new User();
        objUser.setNome(Console.readString("\nInforme o nome do usuário que deseja exluir: \n"));
        objUser = UserPersistencia.procurarPorNome(objUser);
        if(objUser != null) {
            System.out.println("-----------------------");
            System.out.println("ID: " + objUser.getId());
            System.out.println("Nome: " + objUser.getNome());
            System.out.println("-----------------------");
            String resp = Console.readString("\n\nQuer excluir o usuário? ");
            if(resp.equals("S")) {
                if(UserPersistencia.excluir(objUser) == true) {
                    System.out.println("\n O usuário foi excluído!");
                }
                else {
                    System.out.println("\n Não foi possível excluir o usuário...");
                }
            }
        }
        else {
            System.out.println("\n Usuário não registrado.");
        }
    }
}