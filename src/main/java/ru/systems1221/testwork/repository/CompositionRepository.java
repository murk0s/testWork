package ru.systems1221.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.systems1221.testwork.model.Composition;
import ru.systems1221.testwork.model.dto.DailyReportDto;
import ru.systems1221.testwork.model.dto.FoodReportDto;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {

    @Query("SELECT new ru.systems1221.testwork.model.dto.DailyReportDto(" +
            "e.date, e.user.id, SUM(f.kkal), COUNT(DISTINCT e), u.dailyCalorieIntake, u.goal) " +
            "FROM Composition c " +
            "JOIN c.food f " +
            "JOIN c.eating e " +
            "JOIN e.user u " +
            "WHERE e.user.id = :userId AND e.date = :date " +
            "GROUP BY e.user.id, u.dailyCalorieIntake, e.date")
    DailyReportDto calorieReportByUserIdAndDate(@Param("userId") Integer userId,
                                                @Param("date") LocalDate date);

    @Query("SELECT new ru.systems1221.testwork.model.dto.FoodReportDto(e.type, f.name, f.kkal, f.proteins, f.fats, f.carbohydrates, e.date) " +
            "FROM Composition c " +
            "JOIN c.food f " +
            "JOIN c.eating e " +
            "WHERE e.user.id = :userId AND e.date >= :dateBegin AND e.date <= :dateEnd ")
    List<FoodReportDto> calorieReportByUserIdAndDateBeginAndDateEnd(@Param("userId") Integer userId,
                                                                    @Param("dateBegin") LocalDate dateBegin, @Param("dateEnd") LocalDate dateEnd);

}
