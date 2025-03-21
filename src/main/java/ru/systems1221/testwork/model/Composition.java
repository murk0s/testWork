package ru.systems1221.testwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "composition")
public class Composition {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "eating_id")
    private Eating eating;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}