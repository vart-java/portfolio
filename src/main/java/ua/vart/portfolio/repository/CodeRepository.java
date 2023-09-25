package ua.vart.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vart.portfolio.domain.entity.Client;
import ua.vart.portfolio.domain.entity.Code;
import ua.vart.portfolio.domain.enume.CodeStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findCodeByValue(String value);
         Set<Code> findAllByClient(Client client);
}
