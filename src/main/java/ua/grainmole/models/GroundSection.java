package ua.grainmole.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Builder
@Table(name = "ground_section")
@AllArgsConstructor
@NoArgsConstructor
public class GroundSection {

    @Id
    private Integer id;
    @ManyToOne
    private User user;
}
