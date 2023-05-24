package ua.vart.portfolio.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ua.vart.portfolio.domain.base.BaseEntity;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Client extends BaseEntity {

    private String name;

    private String lastName;

    private String code;

    @Singular
    @OneToMany(mappedBy = "client")
    private Set<Feedback> feedbacks;
}
