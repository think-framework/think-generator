package com.hdhxby.ecommerce.auth.repository.impl;

import com.hdhxby.ecommerce.auth.domain.AuthRole;
import com.hdhxby.ecommerce.auth.repository.AuthRoleRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthRoleCriteria;
import com.hdhxby.ecommerce.auth.repository.mapper.AuthRoleMybatisMapper;
import io.github.thinkframework.commons.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限仓库实现.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class AuthRoleRepositoryImpl extends AbstractRepository<AuthRoleMybatisMapper,
    AuthRole, AuthRoleCriteria, Long> implements AuthRoleRepository {

}
