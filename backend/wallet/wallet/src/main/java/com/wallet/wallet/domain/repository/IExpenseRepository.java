package com.wallet.wallet.domain.repository;

import com.wallet.wallet.domain.dto.response.CategoryGroupResponseDto;
import com.wallet.wallet.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 ORDER BY date DESC")
    List<Expense> getAllByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM expenses WHERE category_id = 1 ORDER BY date DESC LIMIT 3")
    List<Expense> getThreeByUserId(Long userId);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND category.id = ?2 ORDER BY date DESC")
    List<Expense> getByCategoryIdByUserId(Long userId, Long categoryId);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND EXTRACT(MONTH FROM date) = ?2")
    List<Expense> getMonthlyByUserId(Long userId, Integer monthNow);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND date BETWEEN ?2 AND ?3 ORDER BY date DESC")
    List<Expense> getSpecificByUserId(Long userId, LocalDate start, LocalDate end);

    //borrar
    //@Query("SELECT category.id, category.name, SUM(e.amount) FROM Expense e WHERE user.id = ?1 GROUP BY category.id ")
    //List<Object> getBalanceByUserIdGroupByCategory(Long userId);

    //borrar
    //@Query("SELECT SUM(e.amount) FROM Expense e WHERE userId.id = ?1 AND EXTRACT(MONTH FROM date) = ?2")
    //Double getBalanceMonthlyByUserId(Long userId, Integer monthNow);

    //borrar
    //@Query("SELECT SUM(e.amount) FROM Expense e WHERE userId.id = ?1 AND date BETWEEN ?2 AND ?3 ORDER BY date DESC")
    //Double getBalanceSpecificByUserId(Long userId, LocalDate start, LocalDate end);

}
