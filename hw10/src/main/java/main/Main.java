package main;

import hiber.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entity.*;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            saveUser(session);
            readUser(session);

        } finally {
            HibernateUtil.close();
        }

    }

    private static void readUser(Session session) {
        User user = session.find(User.class, 1L);
        System.out.println(user.getName());

    }



    private static void saveUser(Session session) {
        var transaction = session.beginTransaction();
        try{
            User user = new User("Masha", 18);
            Phone phone1 = new Phone("+79991632162", user);
            Phone phone2 = new Phone("+79872901009", user);
            Address address = new Address("ул. Сафиуллина", user);

            user.setPhones(List.of(phone1, phone2));
            user.setAddress(address);

            session.persist(user);
            transaction.commit();
            System.out.println("User сохранен");
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
