package ua.vart.portfolio.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.vart.portfolio.domain.dto.ClientCreateDto;
import ua.vart.portfolio.domain.dto.ClientGetDto;
import ua.vart.portfolio.domain.mapper.ClientMapper;
import ua.vart.portfolio.service.ClientService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        var client = clientService.create(clientMapper.toClient(clientCreateDto));
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientGetDto> read(@PathVariable Long id) {
        var client = clientService.findById(id);
        return ResponseEntity.ok(clientMapper.toClientGetDto(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ClientCreateDto clientCreateDto) {
        var client = clientMapper.toClient(clientCreateDto);
        client.setId(id);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<ClientGetDto> findAll(Pageable pageable) {
        var clientsPage = clientService.findAll(pageable);
        return clientMapper.toClientPageDto(clientsPage);
    }
}
