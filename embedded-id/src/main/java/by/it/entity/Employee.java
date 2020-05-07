package by.it.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @EmbeddedId
    @AttributeOverride(name = "firstName", column = @Column(name ="my_firstName"))
    private EmployeeId id;

    private LocalDateTime date;

    public Employee(EmployeeId id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public Employee() {
    }

    public EmployeeId getId() {
        return id;
    }

    public void setId(EmployeeId id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
