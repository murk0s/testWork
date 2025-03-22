package ru.systems1221.testwork.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "composition")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "eating_id")
    private Eating eating;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}