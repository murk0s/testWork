package ru.systems1221.testwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "kkal", nullable = false)
    private Integer kkal;

    @ColumnDefault("0")
    @Column(name = "proteins")
    private Integer proteins;

    @ColumnDefault("0")
    @Column(name = "fats")
    private Integer fats;

    @ColumnDefault("0")
    @Column(name = "carbohydrates")
    private Integer carbohydrates;

    @OneToMany(mappedBy = "food")
    private List<Composition> compositions;

}