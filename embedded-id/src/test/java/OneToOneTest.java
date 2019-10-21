import javax.persistence.EntityManager;

import by.it.entity.EmployeeId;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Employee;
import by.it.util.EMUtil;

import java.time.LocalDateTime;

public class OneToOneTest {
    @Test
    public void saveTest() {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        Employee employee = new Employee(new EmployeeId("Sergey", "Kruk"), LocalDateTime.now());
        em.persist(employee);
        em.getTransaction().commit();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
