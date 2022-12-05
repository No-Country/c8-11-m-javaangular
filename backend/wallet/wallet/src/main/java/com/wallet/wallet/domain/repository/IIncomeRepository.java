package com.wallet.wallet.domain.repository;

import com.wallet.wallet.domain.enums.EIncome;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.model.Income;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IIncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT e FROM Income e WHERE user.id = ?1 ORDER BY date DESC")
    List<Income> getAllByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM incomes WHERE user_id = ?1 ORDER BY date DESC LIMIT 3")
    List<Income> getThreeByUserId(Long userId);

    @Query("SELECT i FROM Income i WHERE user.id = ?1 AND EXTRACT(MONTH FROM date) = ?2 AND EXTRACT(YEAR FROM date) = ?3 AND type != 'Anual'")
    List<Income> getMonthlyByUserId(Long userId, Integer monthNow, Integer yearNow);

    @Query("SELECT i FROM Income i WHERE user.id = ?1 AND EXTRACT(MONTH FROM date) = ?2 AND EXTRACT(YEAR FROM date) = ?3 ORDER BY DATE DESC")
    List<Income> getMonthlyByUserIdForHome(Long userId, Integer monthNow, Integer yearNow);

    @Query("SELECT i FROM Income i WHERE user.id = ?1 AND EXTRACT(YEAR FROM date) = ?2 AND type = 'Anual'")
    List<Income> getYearlyByUserId(Long userId, Integer yearNow);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND type IN ?2 AND date BETWEEN ?3 AND ?4 AND amount BETWEEN ?5 AND ?6")
    List<Income> filterByCategoriesAndDateAndAmount(Long userId, EIncome type, LocalDate start, LocalDate end, Double amountMin, Double amountMax, Sort sort);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND type IN ?2 AND date BETWEEN ?3 AND ?4")
    List<Income> filterByCategoriesAndDate(Long userId, EIncome type, LocalDate start, LocalDate end, Sort sort);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND type IN ?2 AND amount BETWEEN ?3 AND ?4")
    List<Income> filterByCategoriesAndAmount(Long userId, EIncome type, Double amountMin, Double amountMax, Sort sort);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND date BETWEEN ?2 AND ?3 AND amount BETWEEN ?4 AND ?5")
    List<Income> filterByDateAndAmount(Long userId,  LocalDate start, LocalDate end, Double amountMin, Double amountMax, Sort sort);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND type IN ?2")
    List<Income> filterByCategories(Long userId, EIncome type, Sort sort);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND date BETWEEN ?2 AND ?3")
    List<Income> filterByDate(Long userId, LocalDate start, LocalDate end, Sort sort);

    @Query("SELECT e FROM Income e WHERE user.id = ?1 AND amount BETWEEN ?2 AND ?3")
    List<Income> filterByAmount(Long userId,Double amountMin, Double amountMax, Sort sort);


}
