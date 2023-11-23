package ua.grainmole.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "termo_section")
public class TermoSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "height_level" , nullable = false)
    private Integer heightLevel;
    @Column(name = "temperature" , nullable = false)
    private Float temperature;
    @Column(name = "time" , nullable = false)
    private Timestamp time;
    @ManyToOne
    @JoinColumn(name = "grain_section_id")
    private GrainSection grainSection;
}
