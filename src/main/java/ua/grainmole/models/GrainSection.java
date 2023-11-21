package ua.grainmole.models;

import jakarta.persistence.Column;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grain_section")
public class GrainSection {
    @Id
    private BigInteger id;
    @Column(name = "battery_level")
    private Float batteryLevel;
    @Column(name = "air_temperature")
    private Float airTemperature;
    @Column(name = "air_humidity")
    private Float airHumidity;
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;
}
