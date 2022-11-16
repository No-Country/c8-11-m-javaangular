package com.wallet.wallet.api.service.generic;

import com.wallet.wallet.domain.mapper.IMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericServiceAPI<ENT, RES, REQ, ID> {

    RES save(REQ request);

    void delete(ID id);

    JpaRepository<ENT, ID> getRepository();

    IMapper<ENT, RES, REQ> getMapper();
}
