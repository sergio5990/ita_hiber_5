import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.util.EMUtil;

public class OneToManyTest {

    @Test
    public void saveTest() {
        Department department = new Department("SD");
        Employee employee = new Employee(null, "Yulij", "Slabko", null, department);
        department.getEmployees().add(employee);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(department);
        em.flush();
        em.getTransaction().commit();

        em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        department = em.find(Department.class, department.getDepartmentId());

        Employee forDelete = department.getEmployees().iterator().next();

        department.getEmployees().remove(forDelete);
//        forDelete.setDepartment(null);
        em.getTransaction().commit();
    }


    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
