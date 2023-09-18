package ua.vart.portfolio.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ua.vart.portfolio.domain.base.BaseEntity;
import ua.vart.portfolio.domain.enume.CodeStatus;

import java.util.UUID;

import static ua.vart.portfolio.domain.enume.CodeStatus.CREATED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"client", "feedback"})
public class Code extends BaseEntity {
    @Builder.Default
    @Column(unique = true)
    private String value = UUID.randomUUID().toString().substring(0, 6);

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @OneToOne(mappedBy = "code", cascade = CascadeType.PERSIST)
    private Feedback feedback;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CodeStatus codeStatus = CREATED;
}