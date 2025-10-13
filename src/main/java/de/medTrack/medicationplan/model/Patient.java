package de.medTrack.medicationplan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    @Past
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicationPlan medicationPlan;

    public enum Gender {
        MALE, FEMALE, DIVERS
    }

    public void initializeMedicationPlan() {
        if (this.medicationPlan == null) {
            MedicationPlan plan = MedicationPlan.builder()
                    .patient(this)
                    .build();
            this.setMedicationPlan(plan);
        }
    }

}
