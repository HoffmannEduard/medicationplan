package de.medTrack.medicationplan.repository;

import de.medTrack.medicationplan.model.Medication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    @Test
    void testCreateAndFindMedication() {
        //given
        Medication medication = Medication.builder()
                .name("HEXAL Haldol forte Lsg.")
                .activeIngredient("Haloperidol")
                .category("Neuroleptika")
                .contraindication(List.of("Clozapin", "Olanzapin"))
                .sideEffects(List.of("Muskelkrämpfe", "Unruhe"))
                .build();

        //when
        Medication saved = medicationRepository.save(medication);

        //then

        Optional<Medication> found = medicationRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isNotNull();
        assertThat(found.get().getActiveIngredient()).isEqualTo("Haloperidol");

    }


}