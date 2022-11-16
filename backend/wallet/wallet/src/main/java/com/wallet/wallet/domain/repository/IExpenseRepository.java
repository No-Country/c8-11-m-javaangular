package com.wallet.wallet.domain.repository;

import com.wallet.wallet.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {
}
