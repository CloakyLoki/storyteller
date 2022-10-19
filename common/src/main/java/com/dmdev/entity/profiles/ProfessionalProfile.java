package com.dmdev.entity.profiles;

import com.dmdev.entity.Person;
import com.dmdev.entity.Scope;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "scopes")
@EqualsAndHashCode(exclude = {"scopes", "person"})
@Table(name = "professional_profile")
@Builder
public class ProfessionalProfile {

    @Id
    private Integer id;

    @PrimaryKeyJoinColumn
    @OneToOne
    private Person person;
    private String position;
    @Column(name = "hired_since")
    private LocalDate hiredSince;

    @OneToMany(mappedBy = "professionalProfile", cascade = CascadeType.ALL)
    private Set<Scope> scopes;
}