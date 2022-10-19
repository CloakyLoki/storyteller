package com.dmdev.entity;

import com.dmdev.entity.profiles.ProfessionalProfile;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "professionalProfile")
@EqualsAndHashCode(exclude = "professionalProfile")
@Builder
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProfessionalProfile professionalProfile;
}