package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoImp implements CarDao{

    private SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public Car getById(Long id) {
        String q = "from Car car where car.id = :id" ;
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(q);
        query.setParameter("id", id);
        return query.setMaxResults(1).getSingleResult();
    }
}
