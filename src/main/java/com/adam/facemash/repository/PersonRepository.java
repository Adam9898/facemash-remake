package com.adam.facemash.repository;

import com.adam.facemash.dao.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query(value = "SELECT * FROM people WHERE id NOT IN (SELECT person_id FROM users_people WHERE user_id = :userid)",
            nativeQuery = true)
    List<Person> getUserSpecificAvailablePeople(@Param("userid") Long userId);

    @Query(value = "SELECT * FROM people WHERE id IN (SELECT person_id FROM users_people WHERE user_id = :userid " +
            "AND person_id = :personid)",
            nativeQuery = true)
    Person findPersonBySpecificPersonAndUserID(@Param("personid") Long personId, @Param("userid") Long userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_people VALUES (:userid, :personid)", nativeQuery = true)
    void saveVoteContext(@Param("userid") Long userId, @Param("personid") Long personId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE people SET vote_count = vote_count+1 WHERE id = :personid", nativeQuery = true)
    void addVote(@Param("personid") Long personId);

    @Query(value = "SELECT * FROM people ORDER BY vote_count DESC LIMIT 10", nativeQuery = true)
    List<Person> findTop10();


}