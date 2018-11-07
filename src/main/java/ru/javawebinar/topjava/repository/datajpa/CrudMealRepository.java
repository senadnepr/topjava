package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    int deleteByIdAndUser_Id(int id, int userId);

//    @Query("select m from Meal m where m.user.id = :user_id and m.id = :id")
//    Meal get(@Param("id")int id, @Param("user_id")int userId);

    Meal getByIdAndUser_Id(int id, int userId);

//    @Query("select m from Meal m where m.user.id = :user_id ORDER BY m.dateTime DESC")
//    List<Meal> findByUser(@Param("user_id") int userId);
    List<Meal> findByUser_IdOrderByDateTimeDesc(int userId);

//    List<Meal> findByUser_IdAndDateTimeBetweenOrderByDateTimeDesc( int userId, LocalDateTime startDate, LocalDateTime endDate);

    List<Meal> findByDateTimeBetweenAndUser_IdOrderByDateTimeDesc(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
