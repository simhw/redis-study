package com.moduleinfra.repository.user;

import com.moduledomain.command.domain.user.User;
import com.moduledomain.command.domain.user.UserRepository;
import com.moduleinfra.entity.UserJpaEntity;
import com.moduleinfra.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserBy(Long id) {
        UserJpaEntity userEntity = userJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUser(userEntity);
    }
}
