package ua.vart.portfolio.domain.mapper;

import org.springframework.stereotype.Component;
import ua.vart.portfolio.domain.dto.CodeGetDto;
import ua.vart.portfolio.domain.entity.Code;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CodeMapper {
    public CodeGetDto toCodeGetDto(Code code) {
        return new CodeGetDto(code.getId(), code.getValue(), code.getCodeStatus().toString());
    }

    public Set<CodeGetDto> toCodeGetDtoSet(Set<Code> codes) {
        return codes.stream().map(this::toCodeGetDto).collect(Collectors.toSet());
    }
}
