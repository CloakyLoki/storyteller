package com.dmdev.entity;

import com.dmdev.entity.presets.Importance;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"gifts", "stories"})
@EqualsAndHashCode(exclude = {"gifts", "stories"})
@Builder
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