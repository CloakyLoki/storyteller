package com.dmdev.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"person", "tags"})
@EqualsAndHashCode(exclude = {"person", "tags"})
@Builder
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Builder.Default
    @JoinTable(
            name = "gift_tag",
            joinColumns = @JoinColumn(name = "id_gift"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getGifts().add(this);
    }
}