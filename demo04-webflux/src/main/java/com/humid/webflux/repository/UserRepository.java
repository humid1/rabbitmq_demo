package com.humid.webflux.repository;

import com.humid.webflux.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author qiujianping
 * @date Created in 2021/8/13 16:19
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    /**
     * 根据用户年龄查找用户
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age': {'$gte': 20, '$lte': 30}}")
    Flux<User> oldUser();
}
