package ua.vart.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vart.portfolio.domain.entity.Client;
import ua.vart.portfolio.exception.ClientNotFoundException;
import ua.vart.portfolio.repository.ClientRepository;

@RequiredArgsConstructor
@Service
public class ClientService {
    private ClientRepository clientRepository;

    public Client findByCode(String code) {
        return clientRepository.findByCode(code).orElseThrow(() -> new ClientNotFoundException("Client not found by code"));
    }

    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Transactional
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found by id"));
    }

    @Transactional
    public Client update(Client client) {
        if (!clientRepository.existsById(client.getId()))
            throw new ClientNotFoundException("Client not found before update");
        return clientRepository.save(client);
    }
}
