package ru.systems1221.testwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "goal", nullable = false, length = 30)
    @Enumerated(STRING)
    private Goal goal;

    //gender false - male, true - female
    @Column(name = "gender")
    private boolean gender;

    @Column(name = "daily_calorie_intake")
    private int dailyCalorieIntake;

    @OneToMany(mappedBy = "user")
    private List<Eating> eatings;

    public boolean getGender() {
        return gender;
    }

}