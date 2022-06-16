package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "student_course_records")
public class CourseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    CourseRecordKey Id;
    
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    User student;
    
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
    
    @Column(name = "credits", columnDefinition="Float default 0")
    float credits = 0;

    public CourseRecord() {
    }

    public CourseRecordKey getId() {
        return Id;
    }

    public void setId(CourseRecordKey Id) {
        this.Id = Id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseRecord other = (CourseRecord) obj;

        return Objects.equals(this.Id, other.Id);
    }

    @Override
    public String toString() {
        return "CourseRecord{" + "course=" + course + ", credits=" + credits + '}';
    }

    
    
}
