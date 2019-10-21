package by.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MEETING")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private Long meetingId;

    @Column(name = "SUBJECT")
    private String subject;

    @CreationTimestamp
    @Column(name = "DATE")
    private LocalDateTime startDate;

    @ManyToMany(mappedBy = "meetings", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Meeting(String subject) {
        this.subject = subject;
    }

    public Meeting(Long meetingId, String subject, LocalDateTime startDate) {
        this.meetingId = meetingId;
        this.subject = subject;
        this.startDate = startDate;
        this.employees = employees;
    }

    public Meeting() {
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meeting)) return false;

        Meeting meeting = (Meeting) o;

        if (startDate != null ? !startDate.equals(meeting.startDate) : meeting.startDate != null) return false;
        if (meetingId != null ? !meetingId.equals(meeting.meetingId) : meeting.meetingId != null) return false;
        if (subject != null ? !subject.equals(meeting.subject) : meeting.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = meetingId != null ? meetingId.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", subject='" + subject + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
