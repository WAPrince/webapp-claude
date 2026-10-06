package com.example.restservice.greeting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<GreetingEntity, Long> {

	List<GreetingEntity> findByUserId(String userId);
}
