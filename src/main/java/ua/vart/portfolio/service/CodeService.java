package ua.vart.portfolio.service;

import ua.vart.portfolio.domain.entity.Client;
import ua.vart.portfolio.domain.entity.Code;
import java.util.Set;

public interface CodeService {
    Set<Code> findAllByClient(Client client);

    Code create(Client client);

    Code findById(Long id);

    Code findByValue(String value);

    Code use(String value);
}
