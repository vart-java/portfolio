package ua.vart.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vart.portfolio.domain.entity.Client;
import ua.vart.portfolio.domain.entity.Code;
import ua.vart.portfolio.domain.enume.CodeStatus;
import ua.vart.portfolio.exception.CodeAlreadyUsedException;
import ua.vart.portfolio.exception.CodeNotFoundException;
import ua.vart.portfolio.repository.CodeRepository;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;

    public Set<Code> findAllByClient(Client client) {
        return codeRepository.findAllByClient(client);
    }

    public Code create(Client client) {
        return codeRepository.save(Code.builder()
                .client(client)
                .build());
    }

    @Override
    public Code findById(Long id) {
        return codeRepository.findById(id).orElseThrow(() -> new CodeNotFoundException("Code not found with id: " + id));
    }

    public Code findByValue(String value) {
        return codeRepository.findCodeByValue(value).orElseThrow(() -> new CodeNotFoundException("Code not found with value: " + value));
    }

    public Code use(String value) {
        var code = findByValue(value);
        if (!code.getCodeStatus().equals(CodeStatus.CREATED))
            throw new CodeAlreadyUsedException("Code already used with value: " + value);
        code.setCodeStatus(CodeStatus.USED);
        return codeRepository.save(code);
    }
}
