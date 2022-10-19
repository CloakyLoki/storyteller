package com.dmdev.entity;

import com.dmdev.entity.presets.Importance;
import com.dmdev.entity.presets.Religion;
import com.dmdev.entity.profiles.PrivateProfile;
import com.dmdev.entity.profiles.ProfessionalProfile;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"createdBy", "stories"})
@EqualsAndHashCode(exclude = {"createdBy", "stories"})
@Builder
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String patronymic;
    private String surname;
    private LocalDate birthday;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated
    private Importance importance;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private ProfessionalProfile professionalProfile;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PrivateProfile privateProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @Builder.Default
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Story> stories = new HashSet<>();
}