package com.miola.smarthotel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Code created by Andrius on 2020-09-26
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String race;
    private LocalDate birthDate;
    private Boolean isVaccinated;
    private String ownerName;

    @OneToMany(mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Consult> consultSet = new HashSet<>();

    public void addConsult(Consult consult) {
        consultSet.add(consult);
        consult.setPet(this);
    }

    public void removeConsult(Consult consult) {
        consultSet.remove(consult);
        consult.setPet(null);
    }

    public String isVaccinatedStringValue() {
        if(isVaccinated) {
            return "Yes";
        }
        return "No";
    }

    @Override
    public String toString() {
        return String.format("%s (%d) %s", this.race, this.id, this.birthDate);
    }
}
