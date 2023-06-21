package up.edu.br.persistencia;

import up.edu.br.entidades.Task;
import up.edu.br.entidades.ToDo;
import up.edu.br.entidades.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TaskPersistencia {
    public static boolean incluir(Task tarefa) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tarefa);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Task tarefa) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(tarefa);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Task tarefa) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(tarefa);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Task> getTask() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Task");
        List<Task> tasks = consulta.getResultList();
        return tasks;
    }

    public static Task procurarPorTitulo(Task tarefa) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Task where titulo = :param");
        consulta.setParameter("param", tarefa.getTitulo());
        List<Task> tasks = consulta.getResultList();
        if (!tasks.isEmpty()) {
            return tasks.get(0);
        }
        return null;
    }

    public static List<Task> procurarTask(boolean status){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Task where status = :param");
        consulta.setParameter("param", status);
        List<Task> tasks = consulta.getResultList();
        if (!tasks.isEmpty()) {
            return tasks;
        }
        return null;
    }


    public static Task procurarPorId(Task tarefa){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Task where id = :param");
        consulta.setParameter("param", tarefa.getId());
        List<Task> tasks = consulta.getResultList();
        if(!tasks.isEmpty()){
            return tasks.get(0);
        }
        return null;
    }

    public static List<Task> procurarTaskPorUsuario(int usuarioId){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Task where user.id = :param");
        consulta.setParameter("param", usuarioId);
        List<Task> tasks = consulta.getResultList();
        if(!tasks.isEmpty()){
            return tasks;
        }
        return null;
    }


}
