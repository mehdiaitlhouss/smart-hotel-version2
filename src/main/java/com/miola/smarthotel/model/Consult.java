package com.miola.smarthotel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Code created by Andrius on 2020-09-26
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Consult
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate visitDate;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vet_id")
    private Veterinarian veterinarian;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Override
    public String toString () {
        return String.format("%d     %s     %s     %s    %s",
                this.id, this.visitDate.toString(), this.description, veterinarian, pet);
    }

}
