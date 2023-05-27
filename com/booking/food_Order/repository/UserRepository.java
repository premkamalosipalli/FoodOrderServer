package com.booking.food_Order.repository;

import com.booking.food_Order.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    User findByUserName(String userName);

    long countByRole(String role);

    List<User> findAllByRole(String role);

    User findByUserId(long userId);

   void deleteByUserId(long userId);

}
