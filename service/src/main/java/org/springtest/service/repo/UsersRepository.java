package org.springtest.service.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springtest.service.users.User;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<User, String> {
}
