package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public class IncomeServiceImpl extends GenericServiceImpl<Income, Long> implements IIncomeService {


    @Override
    public JpaRepository<Income, Long> getRepository() {
        return null;
    }
}
