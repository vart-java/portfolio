package ua.vart.portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.vart.portfolio.domain.dto.CodeGetDto;
import ua.vart.portfolio.domain.mapper.CodeMapper;
import ua.vart.portfolio.service.ClientService;
import ua.vart.portfolio.service.CodeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/codes")
public class CodeController {
    private final CodeService codeService;
    private final ClientService clientService;
    private final CodeMapper codeMapper;

    @PostMapping
    public ResponseEntity<Void> create(Long clientId) {
        var client = clientService.findById(clientId);
        var code = codeService.create(client);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(code.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeGetDto> read(@PathVariable Long id) {
        var code = codeService.findById(id);
        return ResponseEntity.ok(codeMapper.toCodeGetDto(code));
    }

    @GetMapping("/{value}")
    public ResponseEntity<CodeGetDto> readByValue(@PathVariable String value) {
        var code = codeService.findByValue(value);
        return ResponseEntity.ok(codeMapper.toCodeGetDto(code));
    }

    @PutMapping("/{value}")
    public ResponseEntity<Void> update(@PathVariable String value) {
        codeService.use(value);
        return ResponseEntity.noContent().build();
    }
}
