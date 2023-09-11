package ua.vart.portfolio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.vart.portfolio.domain.entity.Client;

public interface ClientService {
    Page<Client> findAll(Pageable pageable);

    Client create(Client client);

    Client findById(Long id);

    Client update(Client client);

    void delete(Long id);
}
