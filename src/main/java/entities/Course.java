package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "courses", schema="capstone")
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "Credits")
    private Long credits;

    @ManyToOne(cascade = CascadeType.ALL)
    private User teacher;
    
    //@ManyToMany
    //@JoinTable(name = "course_records", joinColumns = @JoinColumn(name = "CourseId"), inverseJoinColumns = @JoinColumn(name = "StudentId"))
    //private List<User> students;
    //@ManyToMany(mappedBy = "coursesTaken")
    //private Collection<User> students;
    
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Collection<CourseRecord> courseRecords;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Collection<CourseRecord> getCourseRecords() {
        return courseRecords;
    }

    public void setCourseRecords(Collection<CourseRecord> courseRecords) {
        this.courseRecords = courseRecords;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", credits=" + credits + ", teacher=" + teacher + '}';
    }
}
