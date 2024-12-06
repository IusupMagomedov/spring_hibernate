package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsersByCarModel(String model) {
      String hql = "FROM User WHERE car.model LIKE :model";
      Session session =sessionFactory.getCurrentSession();
      Query<User> query = session.createQuery(hql);
      query.setParameter("model", "%" + model + "%");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsersByCarSeries(int series) {
      String hql = "FROM User WHERE car.series LIKE :series";
      Session session =sessionFactory.getCurrentSession();
      Query<User> query = session.createQuery(hql);
      query.setParameter("series", series);
      return query.getResultList();
   }
}
