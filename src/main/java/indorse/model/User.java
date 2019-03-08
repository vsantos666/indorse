package indorse.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import indorse.model.base.BaseEntityAudit;
import sun.security.util.Password;

/**
 * Created by vsantos on 7/03/2019.
 */
@Entity
@Table(name="USER",schema = "indorse")
@XmlRootElement
public class User extends BaseEntityAudit{

    @Column(name="NAME", length = 100, nullable = false)
    private String name;

    @Column(name="LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @Column(name="EMAIL", length=100,nullable=false, unique=true, updatable = false)
    private String email;

    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name="LOGIN", length = 30, nullable = false)
    private String login;

    @Column(name="PASSWORD", length = 250, nullable = false)
    private String password;

    public User() {
    }

    public User(String name, String lastName, String email, Date birthDate, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
