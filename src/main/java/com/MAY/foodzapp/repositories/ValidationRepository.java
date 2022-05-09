package com.MAY.foodzapp.repositories;

import com.MAY.foodzapp.entities.AccountValidationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<AccountValidationNumber,Long> {

    Optional<AccountValidationNumber> getValidationByValidationCode(String token);
}
