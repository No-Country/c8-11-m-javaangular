package com.wallet.wallet.domain.repository;


import com.wallet.wallet.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 ORDER BY date DESC")
    List<Expense> getAllByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM expenses WHERE user_id = ?1 ORDER BY date DESC LIMIT 3")
    List<Expense> getThreeByUserId(Long userId);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND category.id = ?2 ORDER BY date DESC")
    List<Expense> getByCategoryIdByUserId(Long userId, Long categoryId);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND EXTRACT(MONTH FROM date) = ?2 AND EXTRACT(YEAR FROM date) = ?3 ORDER BY DATE DESC")
    List<Expense> getMonthlyByUserId(Long userId, Integer monthNow, Integer yearNow);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND date BETWEEN ?2 AND ?3 ORDER BY date DESC")
    List<Expense> getSpecificByUserId(Long userId, LocalDate start, LocalDate end);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND category.id IN ?2 AND amount BETWEEN ?3 AND ?4 AND date BETWEEN ?5 AND ?6")
    List<Expense> filterByCategoriesAndAmountAndDate(Long userId, List<Long> categoriesId, Double amountMin, Double amountMax, LocalDate start, LocalDate end, Sort sort);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND category.id IN ?2 AND amount BETWEEN ?3 AND ?4")
    List<Expense> filterByCategoriesAndAmount(Long userId, List<Long> categoriesId, Double amountMin, Double amountMax, Sort sort);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND category.id IN ?2 AND date BETWEEN ?3 AND ?4")
    List<Expense> filterByCategoriesAndDate(Long userId, List<Long> categoriesId, LocalDate start, LocalDate end, Sort sort);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND amount BETWEEN ?2 AND ?3 AND date BETWEEN ?4 AND ?5")
    List<Expense> filterByAmountAndDate(Long userId, Double amountMin, Double amountMax, LocalDate start, LocalDate end, Sort sort);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND amount BETWEEN ?2 AND ?3")
    List<Expense> filterByAmount(Long userId, Double amountMin, Double amountMax, Sort sort);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND category.id IN ?2")
    List<Expense> filterByCategories(Long userId, List<Long> categoriesId, Sort sort);

    @Query("SELECT e FROM Expense e WHERE user.id = ?1 AND date BETWEEN ?2 AND ?3")
    List<Expense> filterByDate(Long userId, LocalDate start, LocalDate end, Sort sort);

}
