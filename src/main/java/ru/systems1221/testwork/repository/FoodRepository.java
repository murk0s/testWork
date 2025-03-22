package ru.systems1221.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.systems1221.testwork.model.Food;

@RepositoryRestResource(collectionResourceRel = "food", path = "food")
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
