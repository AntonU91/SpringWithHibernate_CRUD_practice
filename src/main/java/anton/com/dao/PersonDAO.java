package anton.com.dao;

import anton.com.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class PersonDAO {
    SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
       Session session=  sessionFactory.getCurrentSession();
      List<Person> people =session.createQuery(" select p from Person p", Person.class).getResultList();
      return people;
    }

    @Transactional (readOnly = true)
    public Person show(int id) {
        Session session=  sessionFactory.getCurrentSession();
      return   session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session=  sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session=  sessionFactory.getCurrentSession();
         Person personToUpdate =session.get(Person.class, id);
         personToUpdate.setName(updatedPerson.getName());
         personToUpdate.setAge(updatedPerson.getAge());
         personToUpdate.setEmail(updatedPerson.getEmail());

    }

    @Transactional
    public void delete(int id) {
        Session session=  sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));

    }
}
