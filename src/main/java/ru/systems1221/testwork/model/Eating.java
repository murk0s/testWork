package ru.systems1221.testwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "eatings")
public class Eating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "type")
    @Enumerated(STRING)
    private EatingType type;

    @OneToMany(mappedBy="eating", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composition> compositions;

}