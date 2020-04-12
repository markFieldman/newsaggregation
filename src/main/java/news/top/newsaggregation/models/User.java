package news.top.newsaggregation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import news.top.newsaggregation.models.enums.AuthProvider;
import news.top.newsaggregation.models.enums.Role;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"password"})
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Enumerated
    private Role role;
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //    @TextIndexed
    private String userName;
    @Email
    private String email;
    private Boolean emailVerified = false;
    //    @TextIndexed
    private String password;
    @NotNull
    @Enumerated
    private AuthProvider provider;
    @CreatedBy
    private String createdBy;
    private String providerId;

    public User(long id, String userName, String email, Boolean emailVerified, String password, @NotNull AuthProvider provider, String providerId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public long getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", id='" + id + '\'' +
                ", name='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", password='" + password + '\'' +
                ", provider=" + provider +
                ", providerId='" + providerId + '\'' +
                '}';
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}
