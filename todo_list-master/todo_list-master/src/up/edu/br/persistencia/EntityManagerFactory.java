package up.edu.br.persistencia;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class EntityManagerFactory {

	private static EntityManager manager;
	
	public static EntityManager getInstance()
	{
		if(manager == null)
		{
			 manager = Persistence.createEntityManagerFactory("todo_list").createEntityManager();
		
		}
		return manager;
	}
		
}
