package de.medTrack.medicationplan.service;

import de.medTrack.medicationplan.model.Medication;
import de.medTrack.medicationplan.model.MedicationPlan;
import de.medTrack.medicationplan.model.Patient;
import de.medTrack.medicationplan.repository.MedicationPlanRepository;
import de.medTrack.medicationplan.repository.MedicationRepository;
import de.medTrack.medicationplan.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MedicationPlanServiceTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    MedicationPlanRepository medicationPlanRepository;
    @Autowired
    MedicationPlanService medicationPlanService;

    Patient patient;

    Medication med1;
    Medication med2;
    Medication contraMed;

    @BeforeEach
    void setUp() {
        //Gemeinsame Testdaten für alle Methoden
        patient = Patient.builder()
                .name("Max")
                .surname("Mustermann")
                .birthday(LocalDate.of(1995, 1, 1))
                .gender(Patient.Gender.MALE)
                .build();
        patient.initializeMedicationPlan();
        patientRepository.save(patient);

        med1 = Medication.builder()
                .name("HEXAL Haldol forte Lsg.")
                .activeIngredient("Haloperidol")
                .category("Neuroleptika")
                .contraindication(List.of("Clozapin", "Olanzapin"))
                .sideEffects(List.of("Muskelkrämpfe", "Unruhe"))
                .build();
        med2 = Medication.builder()
                .name("HEXAL Torem")
                .activeIngredient("Torasemid")
                .category("Diuretika")
                .contraindication(List.of("Candesartan", "Valsartan"))
                .sideEffects(List.of("häufiges Wasserlassen", "Inkontinenz"))
                .build();
        medicationRepository.save(med1);
        medicationRepository.save(med2);

    }

    @Test
    void testAddMedicationToPatientPlan() {
        MedicationPlan plan = medicationPlanService.addMedicationToPatientPlan(patient.getId(), med1);

        assertThat(plan.getMedications()).hasSize(1);
        assertThat(plan.getMedications().getFirst().getName()).isEqualTo("HEXAL Haldol forte Lsg.");
    }

    @Test
    void addMultipleMedications() {
        medicationPlanService.addMedicationToPatientPlan(patient.getId(), med1);
        medicationPlanService.addMedicationToPatientPlan(patient.getId(), med2);

        MedicationPlan plan = medicationPlanRepository.findById(patient.getMedicationPlan().getId())
                        .orElseThrow();

        assertThat(plan.getMedications()).hasSize(2);
        assertThat(plan.getMedications()).extracting("name")
                .containsExactlyInAnyOrder("HEXAL Haldol forte Lsg.", "HEXAL Torem");
    }

}