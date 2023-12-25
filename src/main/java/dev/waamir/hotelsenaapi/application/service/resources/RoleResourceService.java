package dev.waamir.hotelsenaapi.application.service.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.waamir.hotelsenaapi.adapter.dto.resources.role.RoleResponse;
import dev.waamir.hotelsenaapi.domain.model.Role;
import dev.waamir.hotelsenaapi.domain.port.IRoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleResourceService {

    private final IRoleRepository<Role> roleRepository;

    public Page<RoleResponse> list(Pageable pagination) {
        return roleRepository.paginate(pagination).map(RoleResponse::new);
    }
    
}
