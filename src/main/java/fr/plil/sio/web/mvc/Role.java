package fr.plil.sio.web.mvc;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "ROLE_T")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME_F")
    private String name;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "USER_ROLE_T",
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"))
    private Set<User> users = new TreeSet<>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
