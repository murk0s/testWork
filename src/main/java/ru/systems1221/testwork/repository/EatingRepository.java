package ru.systems1221.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.systems1221.testwork.model.Eating;

@Repository
public interface EatingRepository extends JpaRepository<Eating, Integer> {
}
