package com.wallet.wallet.domain.repository;

import com.wallet.wallet.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncomeRepository extends JpaRepository<Income, Long> {
}
