package entity;

import entity.presets.Importance;
import entity.presets.Religion;
import entity.profiles.PrivateProfile;
import entity.profiles.ProfessionalProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"createdBy", "stories"})
@EqualsAndHashCode(exclude = {"createdBy", "stories"})
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