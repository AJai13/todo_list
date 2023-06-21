package up.edu.br.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import up.edu.br.entidades.ToDo;

// Todo user? Arrumar parâmetros
public class ToDoPersistencia {
    public static boolean incluir(ToDo tarefa){
        try{
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tarefa);
            manager.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public static boolean alterar(ToDo tarefa){
        try{
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tarefa);
            manager.getTransaction().commit();
            return true;

        }
        catch(Exception e){
            return false;
        }
    }
    public static boolean excluir(ToDo tarefa){
        try{
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(tarefa);
            manager.getTransaction().commit();
            return true;

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static ToDo procurarPorTitulo(ToDo tarefa){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from ToDo where titulo = :param");
        consulta.setParameter("param", tarefa.getTitulo());
        List<ToDo> tasks = consulta.getResultList();
        if(!tasks.isEmpty()){
            return tasks.get(0);
        }
        return null;
    }


    //modificar 'completed' por 'status' na entidade tbm.
//    public static ToDo procurarPorStatus(ToDo tarefa){
//        EntityManager manager = EntityManagerFactory.getInstance();
//        Query consulta = manager.createQuery("from Todo where status = :param");
//        consulta.setParameter("param", tarefa.getCpf());
//        List<ToDo> tasks = consulta.getResultList();
//        if(!tasks.isEmpty()){
//            return tasks.get(0);
//        }
//        return null;
//    }

    //procurar por descrição tbm?

    //marcar item como completo.


}
