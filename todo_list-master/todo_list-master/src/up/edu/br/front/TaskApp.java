package up.edu.br.front;

import up.edu.br.entidades.Task;
import up.edu.br.entidades.ToDo;
import up.edu.br.persistencia.TaskPersistencia;
import up.edu.br.persistencia.ToDoPersistencia;

import java.util.List;

public class TaskApp {
    public TaskApp(){
        int opc;

        do{
            System.out.println("\n\n******* TAREFAS  ********");
            System.out.println("1 - Adicionar tarefa        ");
            System.out.println("2 - Mostrar tarefa          ");
            System.out.println("3 - Modificar tarefa        ");
            System.out.println("4 - Remover tarefa          ");
            System.out.println("5 - Voltar                  ");
            opc = Console.readInt("Opção:");

            switch(opc){
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    mostrarTarefa();
                    break;
                case 3:
                    modificarTarefa();
                    break;
                case 4:
                    deletarTarefa();
                    break;
                case 5:
                    Principal.main(null);
            }
        }while(opc != 5);
    }
    public static void criarTarefa(){
        System.out.println("******** CRIAÇÃO DA LISTA *********");
        Task objLista = new Task();
        objLista.setTitulo(Console.readString("Digite o titulo da lista:"));
        if (TaskPersistencia.procurarPorTitulo(objLista) == null){
            TaskPersistencia.incluir(objLista);
            System.out.println("Inlusão bem sucedida. ");
        } else {
            System.out.println("Usuário já cadastrado.");
        }
    }


    public static void mostrarTarefa(){
        System.out.println("======================================");
        System.out.println("                LISTA                 ");
        System.out.println("======================================");
        Task objLista = new Task();
        List<Task> tasks = TaskPersistencia.getTask();
        if (!tasks.isEmpty()) {
            System.out.println("----------------------------");
            for (Task x : tasks) {
                System.out.println("|  ID: " +              x.getId());
                System.out.println("|  Titulo: " +      x.getTitulo());
                System.out.println("|                               ");
                System.out.println("| Descrição: :" + x.getConteudo());
                if (x.isStatus()){
                    System.out.println("Status: ✅");
                } else {
                    System.out.println("Status: ❌");
                }
            }
        } else {
            System.out.println("Usuário(s) não encontrado(s).");
        }
    }

    public static void modificarTarefa(){
        System.out.println("=========== MODIFICAR LISTA =============");
        Task objLista = new Task();
        objLista.setTitulo(Console.readString("Digite o titulo da tarefa:"));
        objLista = TaskPersistencia.procurarPorTitulo(objLista);
        if (objLista != null){
            System.out.println("\n====================================");
            System.out.println("ID: "                + objLista.getId());
            System.out.println("Nome: "          + objLista.getTitulo());
            System.out.println("Conteudo: "    + objLista.getConteudo());
            System.out.println("Status: "         + objLista.isStatus());
            System.out.println("=======================================");
            String resp = Console.readString("\n\nQuer alterar " +
                    "o lista? (S/ N)");
            if(resp.equals("S")) {
                System.out.println("\n\n==================================");
                System.out.println(" 1. Titulo                            ");
                System.out.println(" 2. Conteudo                          ");
                System.out.println(" 3. Status                            ");
                System.out.println(" 4. Mostrar a lista novamente.        ");
                System.out.println(" 5. Voltar ao menu principal          ");
                int opcao = Console.readInt("O que deseja alterar?");
                do {
                    switch (opcao){
                        case 1:
                            objLista.setTitulo(Console.readString("Digite o novo titulo " +
                                                                                "para a lista:"));
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...");}
                            break;
                        case 2:
                            objLista.setConteudo(Console.readString("Digite a nova " +
                                                                                    "descrição:"));
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...");
                            }
                            break;
                        case 3:
                            objLista.setStatus(Console.readBoolean("Digite o novo status:"));
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...");
                            }
                            break;
                        case 4:
                            mostrarTarefa();
                            break;
                        case 5:
                            Principal.main(null);
                            break;
                    }
                } while (opcao != 6);
                }
            }

    }

    public static void deletarTarefa(){
        System.out.println("\n\n**** EXCLUSÃO DE LISTA  ****");
        Task objLista = new Task();
        objLista.setTitulo(Console.readString("\nInforme o titulo da lista que deseja exluir: \n"));
        objLista = TaskPersistencia.procurarPorTitulo(objLista);
        if(objLista != null) {
            System.out.println("=====================================================");
            System.out.println("ID: "                               + objLista.getId());
            System.out.println("Titulo: "                       + objLista.getTitulo());
            System.out.println("=====================================================");
            String resp = Console.readString("\n\nQuer excluir a lista? (S/N)");
            if(resp.equals("S")) {
                if(TaskPersistencia.excluir(objLista)) {
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
