package up.edu.br.persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import up.edu.br.entidades.User;

public class UserPersistencia {
    public static boolean incluir(User user){
        try{
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public static boolean alterar(User user){
        try{
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
            return true;

        }
        catch(Exception e){
            return false;
        }
    }
    public static boolean excluir(User user){
        try{
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(user);
            manager.getTransaction().commit();
            return true;

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static List<User> getUsers(){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from User");
        List<User> users = consulta.getResultList();
        return users;
    }

    public static User procurarPorId(User user){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from User where id = :param");
        consulta.setParameter("param", user.getId());
        List<User> users = consulta.getResultList();
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    public static User procurarPorNome(User user){
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from User where nome = :param");
        consulta.setParameter("param", user.getNome());
        List<User> users = consulta.getResultList();
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

}
