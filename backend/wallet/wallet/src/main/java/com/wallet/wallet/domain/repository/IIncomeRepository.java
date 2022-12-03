package com.wallet.wallet.domain.repository;

import com.wallet.wallet.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIncomeRepository extends JpaRepository<Income, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM incomes WHERE user_id = ?1 ORDER BY date DESC LIMIT 3")
    List<Income> getThreeByUserId(Long userId);

    @Query("SELECT i FROM Income i WHERE user.id = ?1 AND EXTRACT(MONTH FROM date) = ?2 AND EXTRACT(YEAR FROM date) = ?3 AND type != 'Anual'")
    List<Income> getMonthlyByUserId(Long userId, Integer monthNow, Integer yearNow);

    @Query("SELECT i FROM Income i WHERE user.id = ?1 AND EXTRACT(MONTH FROM date) = ?2 AND EXTRACT(YEAR FROM date) = ?3 ORDER BY DATE DESC")
    List<Income> getMonthlyByUserIdForHome(Long userId, Integer monthNow, Integer yearNow);

    @Query("SELECT i FROM Income i WHERE user.id = ?1 AND EXTRACT(YEAR FROM date) = ?2 AND type = 'Anual'")
    List<Income> getYearlyByUserId(Long userId, Integer yearNow);

}
