package com.humid.router.repository;

import com.humid.router.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author qiujianping
 * @date Created in 2021/8/17 10:36
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
