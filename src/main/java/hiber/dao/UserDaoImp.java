package hiber.dao;



import hiber.config.AppConfig;
import hiber.model.Car;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("users")
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {

      sessionFactory.getCurrentSession().save(user);

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query =  sessionFactory.getCurrentSession().createQuery("from User");

      return query.getResultList();
   }

   @Override
   public User getUserByCarDetails(Car car) {
      Session session = sessionFactory.openSession();

      Query query =  session.createQuery("from User where  car.series =: series");
      query.setParameter("series",car.getSeries());

      User user = (User) query.uniqueResult();
      session.close();

      return user;
   }

}
