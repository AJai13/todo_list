package up.edu.br.front;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import up.edu.br.entidades.Task;
import up.edu.br.entidades.ToDo;
import up.edu.br.entidades.User;
import up.edu.br.persistencia.TaskPersistencia;
import up.edu.br.persistencia.ToDoPersistencia;
import up.edu.br.persistencia.UserPersistencia;

import java.util.List;

public class TaskApp {
    public TaskApp(){
        int opc;
        do{
            System.out.println("\n\n=========== TAREFAS ===========");
            System.out.println("1 - Criar tarefa                   ");
            System.out.println("2 - Mostrar tarefas                ");
            System.out.println("3 - Modificar tarefa               ");
            System.out.println("4 - Remover tarefa                 ");
            System.out.println("5 - Mostrar tarefas não concluidas ");
            System.out.println("6 - Mostrar tarefa concluidas               ");
            System.out.println("7 - Mostrar tarefas vinculadas a um usuário ");
            System.out.println("8 - Voltar                         ");
            System.out.println("===================================");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1 -> criarTarefa();
                case 2 -> mostrarTarefa();
                case 3 -> modificarTarefa();
                case 4 -> deletarTarefa();
                case 5 -> mostrarTasks(false);
                case 6 -> mostrarTasks(true);
                case 7 -> mostrarTasksPorUsuario();
                case 8 -> Principal.main(null);
            }
        }while(opc != 8);
    }
    public static void criarTarefa() {
        System.out.println("======================================");
        System.out.println("********* CRIAÇÃO DA TAREFA **********");
        System.out.println("======================================");
        Task objLista = new Task();
        objLista.setTitulo(Console.readString("Digite o titulo da Tarefa: "));
        if (TaskPersistencia.procurarPorTitulo(objLista) == null) {
            objLista.setConteudo(Console.readString("Digite a descrição da tarefa: "));
            objLista.setStatus(Console.readBoolean("Caso a tarefa estiver conluida, digite (true), caso contrário (false): "));
            ToDo objlistas = new ToDo();
            objlistas.setTitulo(Console.readString("A qual lista esta tarefa pertence? Caso não tenha nenhuma lista criada, deixe em branco: "));
            String resp = Console.readString("Caso tenha deixado em branco, digite (S) ");
            if(resp.equalsIgnoreCase("S")) {
                System.out.println("\n\n**** Crie uma lista para associar a tarefa. ****");
                Principal.main(null);
            }
            List<User> usuarios = UserPersistencia.getUsers();
            Utilidade.exibirUsuarios(usuarios);
            Integer escolha = Console.readInt("Digite o novo usuário: ");
            String resp2 = Console.readString("Caso tenha deixado em branco, digite (S) ");
            if(resp2.equalsIgnoreCase("S")) {
                System.out.println("\n\n**** Crie um usuário para associar a tarefa. ****");
                Principal.main(null);
            }
            User user_escolha = usuarios.get(escolha);
            objlistas = ToDoPersistencia.procurarPorTitulo(objlistas);
            objLista.setUser(user_escolha);
            if (objlistas != null) {
                objLista.setLista(objlistas);
                System.out.println("Lista: ");
                System.out.println("Titulo : " + objlistas.getTitulo());
            } else {
                System.out.println("Lista não encontrada.");
            }
            if (TaskPersistencia.incluir(objLista) == true) {
                System.out.println("Inlusão bem sucedida. ");
            } else {
                System.out.println("Não foi possível criar tarefa.\n");
            }
        } else {
            System.out.println("Tarefa já criada.");
        }
    }


    public static void mostrarTarefa(){
        System.out.println("======================================");
        System.out.println("                TAREFA                ");
        System.out.println("======================================");
        List<Task> tasks = TaskPersistencia.getTask();
        if (!tasks.isEmpty()) {
            System.out.println("======================================");
            for (Task x : tasks) {
                System.out.println("|  ID: " +                  x.getId());
                System.out.println("|  Titulo: " +          x.getTitulo());
                System.out.println("|  Descrição: "     + x.getConteudo());
                System.out.println("|  Lista pertencente: "+ x.getLista().getTitulo());
                if (x.isStatus()){
                    System.out.println("| Status: ✅");
                } else {
                    System.out.println("| Status: ❌");
                }
            }
        } else {
            System.out.println("Tarefa não encontrada.\n");
        }
    }

    public static void modificarTarefa(){
        System.out.println("=========== MODIFICAR TAREFA =============");
        Task objLista = new Task();
        objLista.setTitulo(Console.readString("\nDigite o titulo da tarefa: "));
        objLista = TaskPersistencia.procurarPorTitulo(objLista);
        if (objLista != null){
            System.out.println("\n====================================");
            System.out.println("ID: "                + objLista.getId());
            System.out.println("Nome: "          + objLista.getTitulo());
            System.out.println("Conteudo: "    + objLista.getConteudo());
            System.out.println("Status: "         + objLista.isStatus());
            System.out.println("=======================================");
            String resp = Console.readString("\n\nQuer alterar " +
                    "a lista? (S/ N) ");
            if(resp.equalsIgnoreCase("S")) {
                int opcao;
                do {
                    System.out.println("\n\n==============================================");
                    System.out.println(" 1. Titulo                                        ");
                    System.out.println(" 2. Conteudo                                      ");
                    System.out.println(" 3. Status                                        ");
                    System.out.println(" 4. Mostrar a lista novamente                     ");
                    System.out.println(" 5. Modificar o status da tarefa                  ");
                    System.out.println(" 6. Modificar a qual lista a tarefa pertence      ");
                    System.out.println(" 7. Voltar ao menu principal                      ");
                    System.out.println(" 8. Voltar ao menu tarefa                         ");
                    System.out.println("==============================================\n\n");
                    opcao = Console.readInt("O que deseja alterar? ");
                    switch (opcao){
                        case 1:
                            objLista.setTitulo(Console.readString("\nDigite o novo titulo " +
                                                                                "para a tarefa: "));
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...");}
                            break;
                        case 2:
                            objLista.setConteudo(Console.readString("\nDigite a nova " +
                                                                                    "descrição: "));
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada...");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");
                            }
                            break;
                        case 3:
                            objLista.setStatus(Console.readBoolean("\nDigite o novo status: "));
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada.\n");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");
                            }
                            break;
                        case 4:
                            mostrarTarefa();
                            break;
                        case 5:
                            objLista.setStatus(Console.readBoolean("Caso a tarefa estiver conluida, digite (true), caso contrário (false):")); //REMOVER
                            if (TaskPersistencia.alterar(objLista)){
                                System.out.println("\n\nAlteração realizada.\n");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");
                            }
                            break;
                        case 6:
                            List<ToDo> objTodo = ToDoPersistencia.getToDo();
                            int contador = 0;
                            if (!objTodo.isEmpty()){
                                System.out.println("========================");
                                System.out.println("Listas: ");
                                for (ToDo x : objTodo) {
                                    System.out.println(contador + ". " + x.getTitulo());
                                    contador++;
                                }
                                System.out.println("========================");}
                            Integer escolha = Console.readInt("Digite o nº da nova lista: ");
                            ToDo lista_escolha = objTodo.get(escolha);
                            objLista.setLista(lista_escolha);
                            if (TaskPersistencia.alterar(objLista)) {
                                System.out.println("\n\nAlteração realizada.");
                            } else {
                                System.out.println("\n\nA alteração não pôde " +
                                        "ser realizada no momento...\n");
                            }

                            break;
                        case 7:
                            Principal.main(null);
                            break;
                    }
                } while (opcao != 8);
                }
            }

    }

    public static void deletarTarefa(){
        System.out.println("\n\n**** EXCLUSÃO DE TAREFA  ****");
        Task objLista = new Task();
        objLista.setTitulo(Console.readString("\nInforme o titulo da tarefa que deseja exluir: \n"));
        objLista = TaskPersistencia.procurarPorTitulo(objLista);
        if(objLista != null)    {
            System.out.println("=====================================================");
            System.out.println("ID: "                               + objLista.getId());
            System.out.println("Titulo: "                       + objLista.getTitulo());
            System.out.println("=====================================================");
            String resp = Console.readString("\n\nQuer excluir a tarefa? (S/N) ");
            if(resp.equalsIgnoreCase("S")) {
                if(TaskPersistencia.excluir(objLista)) {
                    System.out.println("\n A tarefa foi excluída!\n");
                }
                else {
                    System.out.println("\n Não foi possível excluir a tarefa...");
                }
            }
        }
        else {
            System.out.println("\n Tarefa não cadastrada.\n");
        }
    }


    public static void mostrarTasks(boolean status){
        System.out.println("====================================");
        System.out.println("********** STATUS TAREFAS **********");
        System.out.println("====================================");
        List<Task> tasks = TaskPersistencia.getTask();
        tasks = TaskPersistencia.procurarTask(status);
        if (!tasks.isEmpty()) {
            System.out.println("========================");
            if (status) {
                System.out.println("                      ");
                System.out.println("  Tarefas concluidas  ");
                System.out.println("                      ");
                System.out.println("========================");
            } else {
                System.out.println("                      ");
                System.out.println("Tarefas não concluidas");
                System.out.println("                      ");
                System.out.println("========================");
            }
            for (Task x : tasks) {
                    System.out.println("ID: " +         x.getId());
                    System.out.println("Nome: " +   x.getTitulo());
                    System.out.println("Status: " +  x.isStatus());
                    System.out.println("========================");
            }
        } else {
            System.out.println("Usuário(s) não encontrado(s).");
        }

    }

    public static void mostrarTasksPorUsuario(){
        List<User> usuarios = UserPersistencia.getUsers();
        Utilidade.exibirUsuarios(usuarios);
        Integer escolha = Console.readInt("Digite o usuário: ");
        User objUser = usuarios.get(escolha);
        List<Task> usuariosTask = TaskPersistencia.procurarTaskPorUsuario(objUser.getId());
        if (usuariosTask != null){
            for (Task x : usuariosTask) {
                System.out.println("========================");
                System.out.println("ID: " +         x.getId());
                System.out.println("Titulo da tarefa: " + x.getTitulo());
                System.out.println("Status: " +  x.isStatus());
                System.out.println("========================");
            }
        } else {
            System.out.println("\nTarefas vinculadas ao usuário não encontradas.");
        }

    }
    // TAREFAS: ASSOCIAR O USUARIO COM A TAREFA, LISTA
    // INCREMENTAR A FUNÇÃO, MOSTRAR TAREFAS A CONCLUIR DA LISTA
    // MOSTRAR TAREFA CONCLUIDAS
    // LISTA - SETA O NOME, E A QUAL USUARIO ESTÁ, NÃO PRECISA DE DESC - OK
    // INCREMENTAR FUNCAO - MOSTRAR TAREFAS ALOCADAS A CERTO USUARIO (TODAS, DE TODAS AS LISTAS) - NÃO OK
}
