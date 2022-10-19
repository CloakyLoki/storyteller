package com.dmdev.entity;

import com.dmdev.entity.presets.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "addedPersons")
@EqualsAndHashCode(exclude = "addedPersons")
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String login;
    private String password;
    private String patronymic;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "createdBy")
    private List<Person> addedPersons;

}