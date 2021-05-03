package com.github.nevardima.flightsmonitoring.repository;

import com.github.nevardima.flightsmonitoring.repository.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByEmail(String email);
}
