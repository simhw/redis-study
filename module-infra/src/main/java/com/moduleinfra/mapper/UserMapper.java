package com.moduleinfra.mapper;

import com.moduledomain.command.domain.user.User;
import com.moduleinfra.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "deletedAt", target = "deletedAt")
    User toUser(UserJpaEntity userJpaEntity);

    UserJpaEntity toUserEntity(User user);
}
