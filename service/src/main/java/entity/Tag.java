package entity;

import entity.presets.Importance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"gifts", "stories"})
@EqualsAndHashCode(exclude = {"gifts", "stories"})
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @Enumerated
    private Importance importance;

    @Builder.Default
    @ManyToMany(mappedBy = "tags")
    private Set<Gift> gifts = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "tags")
    private Set<Story> stories = new HashSet<>();
}