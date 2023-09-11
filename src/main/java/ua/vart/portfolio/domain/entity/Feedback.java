package ua.vart.portfolio.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ua.vart.portfolio.domain.base.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Feedback extends BaseEntity {

    private String text;

    @OneToOne
    @JoinColumn(name = "code_id")
    private Code code;
}
