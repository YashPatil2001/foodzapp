package com.MAY.foodzapp.repositories;

import com.MAY.foodzapp.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {


    List<Profile> findByFirstName(String name);

    List<Profile> findByFirstNameIgnoreCase(String name);

    Optional<Profile> findByEmail(String email);
}
