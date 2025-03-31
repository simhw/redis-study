package com.moduleinfra.mapper;

import com.moduledomain.command.domain.user.User;
import com.moduleinfra.entity.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserJpaEntity userJpaEntity);

    UserJpaEntity toUserEntity(User user);
}
