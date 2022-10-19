package com.dmdev.entity.profiles;

import com.dmdev.entity.Person;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "person")
@Builder
@Table(name = "private_profile")
public class PrivateProfile {

    @Id
    private Integer id;
    private LocalDate wedding;

    @PrimaryKeyJoinColumn
    @OneToOne
    private Person person;
}