package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();

    }

    @Override
    public User getById(Long id) {
        String q = "from User user where user.id = :id" ;
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("id", id);
        return query.setMaxResults(1).getSingleResult();
    }

    @Override
    public User getUserByCar(String model, int series) {
        String q = "select id from Car car where car.model = :model and car.series = :series";
        TypedQuery<Long> query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("model", model).setParameter("series", series);
        return getById(query.setMaxResults(1).getSingleResult());
    }

}
