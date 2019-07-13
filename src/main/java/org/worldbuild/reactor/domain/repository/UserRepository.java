package org.worldbuild.reactor.domain.repository;

import org.worldbuild.reactor.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User ,String> {

    Flux<User> findByName(String name);
}
