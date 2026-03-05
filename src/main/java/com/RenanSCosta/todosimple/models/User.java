package com.RenanSCosta.todosimple.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    public interface createUser {}
    public interface updateUser {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = createUser.class)
    @NotEmpty(groups = createUser.class)
    @Size(groups = createUser.class, min = 3, max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY) // impede que a senha seja exposta em respostas JSON
    @Column(name = "password", length = 255, nullable = false)
    @NotNull(groups = {createUser.class, updateUser.class})
    @NotEmpty (groups = {createUser.class, updateUser.class})
    @Size(groups = {createUser.class, updateUser.class}, min = 8, max = 255)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();


    public User(){}
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


}
