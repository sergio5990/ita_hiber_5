import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Employee;
import by.it.entity.EmployeeDetail;
import by.it.util.EMUtil;

public class OneToOneTest {
    @Test
    public void saveTest() {
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "",
                "Belarus", employee);
        employee.setEmployeeDetail(employeeDetail);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();

        em.clear();

        Employee employeeFromDb = em.find(Employee.class, 1L);
        Assert.assertEquals(employee.getFirstName(), employeeFromDb.getFirstName());
    }

    @Test
    public void mergeCascadeTest() {
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "", "Belarus", employee);
        employee.setEmployeeDetail(employeeDetail);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();

        em.clear();

        Employee employeeFromDb = em.find(Employee.class, 1L);

        employeeFromDb.getEmployeeDetail().setCity("Kiev");
        em.getTransaction().begin();
        em.merge(employeeFromDb);
        em.getTransaction().commit();

        Assert.assertEquals(employee.getFirstName(), employeeFromDb.getFirstName());
    }

    @Test
    public void removeCascadeTest() {
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "", "Belarus", employee);
        employee.setEmployeeDetail(employeeDetail);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
