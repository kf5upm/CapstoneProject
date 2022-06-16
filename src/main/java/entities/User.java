package entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema="capstone")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role.title = :role")
})
public class User implements Serializable {
    private static final Logger logger = LogManager.getLogger();

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "FirstName", length = 50, nullable = false, unique = false)
    private String firstName;

    @Column(name = "LastName", length = 50, nullable = false, unique = false)
    private String lastName;
    
    @Column(name = "Gender", length = 10, nullable = false, unique = false)
    private String gender;
    
    @ManyToOne
    private Role role;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Collection<Course> coursesTaught;
//    
//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name = "student_course_records", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private Collection<Course> coursesTaken;
//    
    @OneToMany(mappedBy = "student", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CourseRecord> courseRecords;
    
    @Column(name = "GPA")
    private float gpa;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
    
    public Collection<Course> getCoursesTaught() {
        return coursesTaught;
    }
//
//    public Collection<Course> getCoursesTaken() {
//        return coursesTaken;
//    }
//    
//    public void setCoursesTaken(Collection<Course> selected) {
//        if (selected == null) {
//            this.coursesTaken = null;
//        } else {
//            this.coursesTaken = selected;            
//        }
//    }
//
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", courseRecords=" + courseRecords + '}';
    }
}
