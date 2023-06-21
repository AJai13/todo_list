package up.edu.br.front;

import up.edu.br.entidades.Task;
import up.edu.br.entidades.ToDo;
import up.edu.br.entidades.User;
import up.edu.br.persistencia.TaskPersistencia;
import up.edu.br.persistencia.ToDoPersistencia;
import up.edu.br.persistencia.UserPersistencia;

import java.util.List;
//LINKAR COM USUÁRIO
public class ToDoApp {
    public ToDoApp() {
        int opc;

        do {
            System.out.println("============  LISTAS  ============");
            System.out.println("                                  ");
            System.out.println("1 - Adicionar lista               ");
            System.out.println("2 - Mostrar lista                 ");
            System.out.println("3 - Modificar lista               ");
            System.out.println("4 - Excluir lista                 ");
            System.out.println("5 - Voltar                        ");
            System.out.println("==================================");
            opc = Console.readInt("Opção:");

            switch (opc) {
                case 1:
                    adicionarLista();
                    break;
                case 2:
                    mostrarLista();
                    break;
                case 3:
                    modificarLista();
                case 4:
                    deletarLista();
                    break;
                case 5:
                    Principal.main(null);
            }
        } while (opc != 7);
    }

    public static void adicionarLista(){
        System.out.println("======================================");
        System.out.println("         ADICIONAR A LISTA            ");
        System.out.println("======================================");
        ToDo objLista = new ToDo();
        objLista.setTitulo(Console.readString("\nInforme o título da sua lista: "));
        ToDo objBusca = ToDoPersistencia.procurarPorTitulo(objLista);
        if(objBusca == null) {
            User objUser = new User();
            objUser.setNome(Console.readString("A qual usuário a lista está associada?: "));
            if (objUser != null){
                objLista.setUser(objUser);
                System.out.println("Usuário: ");
                System.out.println("ID: "     + objUser.getId());
                System.out.println("Nome: " + objUser.getNome());

            } else {
                System.out.println("Usuario não encontrado. ");
            }

            if (ToDoPersistencia.incluir(objLista) == true) {
                System.out.println("Lista adicionada com sucesso.\n");
            } else {
                System.out.println("\nNão foi possível adicionar a lista.");
            }
        }
            else {
            System.out.println("Lista já criada...\n");
        }
    }


    public static void mostrarLista(){
        System.out.println("======================================");
        System.out.println("                LISTA                 ");
        System.out.println("======================================");
        ToDo objLista = new ToDo();
        List<ToDo> listas = ToDoPersistencia.getToDo();
        if (!listas.isEmpty()) {
            System.out.println("---------------------------------");
            for (ToDo x : listas) {
                System.out.println("|  ID: " +              x.getId());
                System.out.println("|  Titulo: " +      x.getTitulo());
                System.out.println("|  Descrição: " + x.getConteudo());
                System.out.println("|  Usuario: " + x.getUser().getNome());
                System.out.println("---------------------------------\n\n");
            }
        } else {
            System.out.println("Lista não encontrada.\n");
        }
    }

    public static void modificarLista(){
        System.out.println("=========== MODIFICAR LISTA =============");
        ToDo objLista = new ToDo();
        objLista.setTitulo(Console.readString("Digite o titulo da tarefa:"));
        objLista = ToDoPersistencia.procurarPorTitulo(objLista);
        if (objLista != null){
            System.out.println("\n====================================");
            System.out.println(" ID: "               + objLista.getId());
            System.out.println(" Nome: "         + objLista.getTitulo());
            System.out.println(" Conteudo: "   + objLista.getConteudo());
            System.out.println("=======================================");
            String resp = Console.readString("\n\nQuer alterar " +
                    "a lista? (S/ N): \n");
            if(resp.equalsIgnoreCase("S")) {
                int opcao;
                do {
                    System.out.println("\n\n==================================");
                    System.out.println(" 1. Titulo                            ");
                    System.out.println(" 2. Conteudo                          ");
                    System.out.println(" 3. Mostrar a lista novamente.        ");
                    System.out.println(" 4. Modificar usuário atribuído       ");
                    System.out.println(" 5. Voltar ao menu principal          ");
                    System.out.println(" 6. Sair                              ");
                    System.out.println("====================================\n");
                    opcao = Console.readInt("O que deseja alterar?");

                    switch (opcao){
                        case 1:
                            objLista.setTitulo(Console.readString("Digite o novo titulo " +
                                    "para a lista:"));
                            if (ToDoPersistencia.alterar(objLista) == true){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");}
                            break;
                        case 2:
                            objLista.setConteudo(Console.readString("Digite a nova " +
                                    "descrição:"));
                            if (ToDoPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");
                            }
                            break;
                        case 3:
                            mostrarLista();
                            break;
                        case 4:
                            List<User> usuarios = UserPersistencia.getUsers();
                            Utilidade.exibirUsuarios(usuarios);
                            Integer escolha = Console.readInt("Digite o novo usuário: ");
                            User user_escolha = usuarios.get(escolha);
                            objLista.setUser(user_escolha);
                            if (ToDoPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");
                            }
                            break;
                        case 5:
                            Principal.main(null);
                            break;
                    }
                } while (opcao != 6);
                // 5 - voltar está chamando Exclusão de lista. WHY?
            }
        }

    }

    public static void deletarLista(){
        System.out.println("\n\n**** EXCLUSÃO DE LISTA  ****");
        ToDo objLista = new ToDo();
        objLista.setTitulo(Console.readString("\nInforme o titulo da lista que deseja exluir: \n"));
        objLista = ToDoPersistencia.procurarPorTitulo(objLista);
        if(objLista != null) {
            System.out.println("=====================================================");
            System.out.println("ID: "                               + objLista.getId());
            System.out.println("Titulo: "                       + objLista.getTitulo());
            System.out.println("=====================================================");
            String resp = Console.readString("\n\nQuer excluir a lista? (S/N)");
            if(resp.equalsIgnoreCase("S")) {
                if(ToDoPersistencia.excluir(objLista)) {
                    System.out.println("\n A lista foi excluída!\n");
                }
                else {
                    System.out.println("\n Não foi possível excluir a lista...\n");
                }
            }
        }
        else {
            System.out.println("\n Lista não cadastrada.\n");
        }
    }
}
