package indorse.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserDTO {

    private Long id;

    @NotBlank(message = "Name cannot be null or blank")
    @Size(min = 2,max = 25,message = "Name must be between 2 and 25 characters")
    private String name;

    @NotBlank(message = "Last Name cannot be null or blank")
    @Size(min = 2,max = 25,message = "Last Name must be between 2 and 25 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be null or blank")
    @Email(message = "Email should be valid")
    private String email;

    private Date birthDate;

    @NotBlank(message = "UserLogin cannot be null or blank")
    @Size(min = 6,max = 8,message = "UserLogin must be between 6 and 8 characters")
    private String login;

    @NotBlank(message = "Password cannot be null or blank")
    @Size(min = 6,max = 8,message = "Password must be between 6 and 8 characters")
    private String password;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String lastName, String email, Date birthDate, String login, String password) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
