package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseRecordKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "student_id")
    Long studentId;
    
    @Column(name = "course_id")
    Long courseId;

    public CourseRecordKey() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.studentId);
        hash = 67 * hash + Objects.hashCode(this.courseId);
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
        final CourseRecordKey other = (CourseRecordKey) obj;
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        return Objects.equals(this.courseId, other.courseId);
    }
}
