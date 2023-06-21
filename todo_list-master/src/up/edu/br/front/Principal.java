package up.edu.br.front;

public class Principal {
	public static void main(String[] args) {
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("====== MENU PRINCIPAL ======");
			System.out.println("1 - Usuario                 ");
			System.out.println("2 - Listas                  ");
			System.out.println("3 - Tarefas   		        ");
			System.out.println("4 - Sair			        ");
			System.out.println("============================");
			opc = Console.readInt("Opção: ");
			switch (opc) {
				case 1 -> new UserApp();
				case 2 -> new ToDoApp();
				case 3 -> new TaskApp();
			}
		}while(opc != 4);
	}
}