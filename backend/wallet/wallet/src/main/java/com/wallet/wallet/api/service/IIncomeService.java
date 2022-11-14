package com.wallet.wallet.api.service;

import com.wallet.wallet.api.service.generic.GenericServiceAPI;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.model.Income;

public interface IIncomeService extends GenericServiceAPI<Income, Long> {

    //IncomeResponseDto getFindByDate();
}
