package up.edu.br.front;

import up.edu.br.entidades.Task;
import up.edu.br.entidades.ToDo;
import up.edu.br.entidades.User;
import up.edu.br.persistencia.TaskPersistencia;
import up.edu.br.persistencia.ToDoPersistencia;
import up.edu.br.persistencia.UserPersistencia;

import java.util.List;

public class ToDoApp {
    public ToDoApp() {
        int opc;

        do {
            System.out.println("========  LISTAS  ==========");
            System.out.println("                            ");
            System.out.println("1 - Adicionar lista         ");
            System.out.println("2 - Listar lista            ");
            System.out.println("3 - Modificar lista         ");
            System.out.println("4 - Excluir lista           ");
            System.out.println("5 - Voltar                  ");
            System.out.println("============================");
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
        } while (opc != 5);
    }

    public static void adicionarLista(){
        System.out.println("======================================");
        System.out.println("         ADICIONAR A LISTA            ");
        System.out.println("======================================");
        ToDo objLista = new ToDo();
        objLista.setTitulo(Console.readString("\nInforme o título da sua lista: "));
        objLista = ToDoPersistencia.procurarPorTitulo(objLista);
        if(objLista != null){
            objLista.setTitulo();
            Task objTask = new Task();
            objTask.setTitulo(Console.readString("Informe o titulo da tarefa: "));
            objTask = TaskPersistencia.procurarPorTitulo(objTask);
            if (objTask != null){
                objTask.setTitulo(objTask);
                objTask.setConteudo(Console.readString("Informe o conteudo da tarefa: \n"));
            } else {
                System.out.println("Tarefa já cadastrada..");
            }
            User objUser = new User();
            objUser.setNome(Console.readString("Informe o nome do Usuário: "));
            objUser = UserPersistencia.procurarPorNome(objUser);
            if (objUser != null){

            } else {
                System.out.println("Usuário não cadastrado...");
            }
        } else {
            System.out.println("Lista já criada...");
        }
    }


    public static void mostrarLista(){
        System.out.println("======================================");
        System.out.println("                LISTA                 ");
        System.out.println("======================================");
        ToDo objLista = new ToDo();
        List<ToDo> listas = (List<ToDo>) ToDoPersistencia.procurarPorTitulo(objLista);
        if (!listas.isEmpty()) {
            System.out.println("---------------------------------");
            for (ToDo x : listas) {
                System.out.println("|  ID: " +              x.getId());
                System.out.println("|  Titulo: " +      x.getTitulo());
                System.out.println("| Descrição: :" + x.getConteudo());
                System.out.println("---------------------------------\n\n");
                //FAZER LINK COM TAREFAS (PEGAR STATUS DAS TAREFAS DO BANCO DE DADOS);
//                if (x.isStatus()){
//                    System.out.println("Status: ✅");
//                } else {
//                    System.out.println("Status: ❌");
//                }
//            }
//        } else {
//            System.out.println("Usuário(s) não encontrado(s).");
//        }
    }}}

    public static void modificarLista(){
        System.out.println("=========== MODIFICAR LISTA =============");
        ToDo objLista = new ToDo();
        objLista.setTitulo(Console.readString("Digite o titulo da tarefa:"));
        objLista = ToDoPersistencia.procurarPorTitulo(objLista);
        if (objLista != null){
            System.out.println("\n====================================");
            System.out.println("ID: "                + objLista.getId());
            System.out.println("Nome: "          + objLista.getTitulo());
            System.out.println("Conteudo: "    + objLista.getConteudo());
            System.out.println("=======================================");
            String resp = Console.readString("\n\nQuer alterar " +
                    "o lista? (S/ N)");
            if(resp.equals("S")) {
                System.out.println("\n\n==================================");
                System.out.println(" 1. Titulo                            ");
                System.out.println(" 2. Conteudo                          ");
                System.out.println(" 3. Mostrar a lista novamente.        ");
                System.out.println(" 4. Voltar ao menu principal          ");
                System.out.println(" 5. Sair                              ");
                int opcao = Console.readInt("O que deseja alterar?");
                do {
                    switch (opcao){
                        case 1:
                            objLista.setTitulo(Console.readString("Digite o novo titulo " +
                                    "para a lista:"));
                            if (ToDoPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...");}
                            break;
                        case 2:
                            objLista.setConteudo(Console.readString("Digite a nova " +
                                    "descrição:"));
                            if (ToDoPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...");
                            }
                            break;
                        case 3:
                            mostrarLista();
                            break;
                        case 4:
                            Principal.main(null);
                            break;
                    }
                } while (opcao != 5);
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
            if(resp.equals("S")) {
                if(ToDoPersistencia.excluir(objLista)) {
                    System.out.println("\n A lista foi excluída!");
                }
                else {
                    System.out.println("\n Não foi possível excluir a lista...");
                }
            }
        }
        else {
            System.out.println("\n Lista não cadastrada.");
        }
    }
}
