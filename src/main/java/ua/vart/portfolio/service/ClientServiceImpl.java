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
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found by id: " + id));
    }

    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Transactional
    public Client update(Client client) {
        if (!clientRepository.existsById(client.getId()))
            throw new ClientNotFoundException("Client not found before update, id: " + client.getId());
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long clientId) {
        if (clientRepository.existsById(clientId)) clientRepository.deleteById(clientId);
        else throw new ClientNotFoundException("Client not found before delete, id: " + clientId);
    }
}
