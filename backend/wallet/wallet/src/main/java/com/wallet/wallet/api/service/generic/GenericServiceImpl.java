package com.wallet.wallet.api.service.generic;

import com.wallet.wallet.domain.mapper.IMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl <ENT, RES, REQ, ID> implements GenericServiceAPI<ENT, RES, REQ, ID>{

    @Override
    public RES save(REQ request) {
        return getMapper().entityToResponseDto(getRepository().save(getMapper().requestDtoToEntity(request)));
    }

    @Override
    public void delete(ID id){getRepository().deleteById(id);
    }

    @Override
    public abstract JpaRepository<ENT, ID> getRepository();

    @Override
    public abstract IMapper<ENT, RES, REQ> getMapper();
}
