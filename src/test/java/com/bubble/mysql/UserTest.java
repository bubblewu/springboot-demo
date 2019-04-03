package com.bubble.mysql;

import com.bubble.dao.mysql.UserRepository;
import com.bubble.domain.entity.user.UserEntity;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * 基于Spring Data JPA的User操作demo
 *
 * @author wugang
 * date: 2019-04-03 10:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity genUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setPwd("123456");
        return userEntity;
    }

    private UserEntity genUser2() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("hello");
        userEntity.setPwd("123456");
        return userEntity;
    }

    @Test
    public void testDateTime() {
        UserEntity user = userRepository.save(genUser());
        assertNotNull(user.getCreatedDate());
        assertNotNull(user.getModifiedDate());
    }

    @Test
    public void testSaveAndFindById() {
        UserEntity user = userRepository.save(genUser());
        Optional<UserEntity> opt = userRepository.findById(user.getId());
        assertTrue(opt.isPresent());
        assertEquals(opt.get().getName(), "test");
        assertFalse(userRepository.findById(user.getId() + 1).isPresent());
    }

    @Test
    public void testFindByName() {
        UserEntity user = userRepository.save(genUser());
        Optional<UserEntity> result = userRepository.findByName(user);
        assertEquals(result.get(), user);
    }

    @Test
    public void testStreamAllUsers() {
        List<UserEntity> list = Lists.newArrayList();
        UserEntity user1 = userRepository.save(genUser());
        UserEntity user2 = userRepository.save(genUser2());
        list.add(user1);
        list.add(user2);
        try (Stream<UserEntity> stream = userRepository.streamAllUsers()) {
            assertTrue(stream.collect(Collectors.toList()).containsAll(list));
        }
    }

    @Test
    public void useFindAllByNameIsNotNull() {
        List<UserEntity> list = Lists.newArrayList();
        UserEntity user1 = userRepository.save(genUser());
        UserEntity user2 = userRepository.save(genUser2());
        list.add(user1);
        list.add(user2);

        try (Stream<UserEntity> stream = userRepository.findAllByNameIsNotNull()) {
            assertTrue(stream.collect(Collectors.toList()).containsAll(list));
        }
    }

    // 不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED)
    // NOT_SUPPORTED:声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行。
    @Test(expected = InvalidDataAccessApiUsageException.class)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void rejectsStreamExecutionIfNoSurroundingTransactionActive() {
        userRepository.findAllByNameIsNotNull();
    }


    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testAsyncReadAllBy() throws InterruptedException, ExecutionException {
        userRepository.save(genUser());
        userRepository.save(genUser2());
        CompletableFuture<Void> future = userRepository.readAllBy().thenAccept(users -> {
            assertTrue(users.size() >= 2);
            users.forEach(user -> System.out.println(user.toString()));
            System.out.println("completed");
        });
        while (!future.isDone()) {
            System.out.println("Waiting for the CompletableFuture to finish...");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        future.get();
        System.out.println("job done");

    }

}
