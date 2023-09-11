package ua.vart.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vart.portfolio.domain.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
