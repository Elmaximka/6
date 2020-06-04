package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
    public void deleteUser(String name){
        sessionFactory.getCurrentSession().createQuery("delete from User where firstName = :name")
                .setParameter("name", name)
                .executeUpdate();
    }
    public User getUserByName(String name){
        return (User)sessionFactory.getCurrentSession().createQuery("from User where name = :name ")
                .setParameter("name", name)
                .getSingleResult();
    }

}
