package org.springtest.spring5test.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springtest.spring5test.users.User;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<User, String> {
}
