package dmdev.entity.profiles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "personal_profile")
public class PersonalProfile {

    @Id
    private Integer id;
    private LocalDate birthday;
    private String nationality;
    private String religion;
}