package de.medTrack.medicationplan.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String activeIngredient;
    private String category;

    @ElementCollection
    private List<String> contraindication;

    @ElementCollection
    private List<String> sideEffects;

}
