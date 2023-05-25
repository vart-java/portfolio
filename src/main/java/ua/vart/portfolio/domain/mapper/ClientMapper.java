package ua.vart.portfolio.domain.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ua.vart.portfolio.domain.dto.ClientCreateDto;
import ua.vart.portfolio.domain.dto.ClientGetDto;
import ua.vart.portfolio.domain.entity.Client;

@Component
public class ClientMapper {
    public ClientGetDto toClientGetDto(Client client) {
        return new ClientGetDto(client.getId(), client.getName(), client.getLastName(), client.getCode());
    }

    public Client toClient(ClientCreateDto clientCreateDto) {
        return Client.builder()
                .name(clientCreateDto.name())
                .lastName(clientCreateDto.lastName())
                .build();
    }

    public Page<ClientGetDto> toClientPageDto(Page<Client> clientPage) {
        return new PageImpl<ClientGetDto>(
                clientPage.getContent().stream().map(this::toClientGetDto).toList(),
                clientPage.getPageable(),
                clientPage.getTotalElements()
        );
    }
}
