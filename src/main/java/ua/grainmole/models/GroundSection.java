package ua.grainmole.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;


@Getter
@Setter
@Entity
@Table(name = "ground_section")
@AllArgsConstructor
@NoArgsConstructor
public class GroundSection {

    @Id
    private BigInteger id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
}
