package com.bubble.dao.mysql;

import com.bubble.domain.entity.user.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Spring Data JPA 操作MySQL的user表
 *
 * @author wugang
 * date: 2019-04-03 10:28
 **/
public interface UserRepository extends Repository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>, Serializable {
// UserRepository继承了JpaRepository接口（SpringDataJPA提供的简单数据操作接口）;
// JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口）
// Serializable（序列化接口）。


//    @Override
//    Optional<UserEntity> findOne(Specification<UserEntity> spec);

    Optional<UserEntity> findById(long id);

    <S extends UserEntity> S save(S user);

    Optional<UserEntity> findByName(String name);

    default Optional<UserEntity> findByName(UserEntity user) {
        return findByName(user == null ? null : user.getName());
    }

    @Query("select u from UserEntity u")
    List<UserEntity> findAll();

    @Query("select u from UserEntity u")
    Stream<UserEntity> streamAllUsers();

    Stream<UserEntity> findAllByNameIsNotNull();

    @Async
    CompletableFuture<List<UserEntity>> readAllBy();

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.pwd = ?2 where u.name = ?1")
    int updatePwdByName(String name, String pwd);

    @Modifying
    @Transactional
    @Query("delete from UserEntity u where u.name = ?1")
    int deleteByName(String name);

}
