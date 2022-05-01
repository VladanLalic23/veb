package projekat.veb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    @Column
    private String password;

    @Column
    private String FirstName;

    @Column
    private String LastName;

    @Column
    private String gender;

    @Column
    private String birthDate;

    @Column
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getRole() {
        return role;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", FirstName='" + getFirstName() + "'" +
                ", LastName='" + getLastName() + "'" +
                ", email='" + getgender() + "'" +
                ", birthDate='" + getBirthDate() + "'" +
                ", role='" + getRole() + "'" +
                "}";
    }

}
