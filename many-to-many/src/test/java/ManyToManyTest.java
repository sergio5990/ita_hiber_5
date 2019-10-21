import by.it.entity.Employee;
import by.it.entity.Meeting;
import by.it.util.EMUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class ManyToManyTest {

    @Test
    public void saveTest() {
        Employee employee1 = new Employee(null, "Yulij", "Slabko", null);
        Employee employee2 = new Employee(null, "Sergey", "Kruk", null);
        final ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        final Meeting meeting1 = new Meeting(null, "task 1", LocalDateTime.now());
        final Meeting meeting2 = new Meeting(null, "task 2", LocalDateTime.now().plusDays(1));
        final ArrayList<Meeting> meetings = new ArrayList<>();
        meetings.add(meeting1);
        meetings.add(meeting2);

        employee1.getMeetings().addAll(meetings);
        employee2.getMeetings().addAll(meetings);

        meeting1.getEmployees().addAll(employees);
        meeting2.getEmployees().addAll(employees);


        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(meeting1);
        em.persist(meeting2);
        em.getTransaction().commit();
        em.clear();
        Meeting meeting = em.find(Meeting.class, 1L);
        meeting.getEmployees().size();

    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
