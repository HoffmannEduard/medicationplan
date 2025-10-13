package de.medTrack.medicationplan.service;

import de.medTrack.medicationplan.model.Patient;
import de.medTrack.medicationplan.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    private PatientService patientService;

    @BeforeEach
    void setUp() {
        patientService = new PatientService(patientRepository);
    }

    @Test
    void testCreatePatientWithEmptyMedicationPlan() {
        LocalDate birthday = LocalDate.of(1995, 1, 1);
        Patient patient = patientService.createPatient("Max", "Mustermann", birthday, Patient.Gender.MALE);

        assertThat(patient.getId()).isNotNull();
        assertThat(patient.getBirthday()).isEqualTo(birthday);
        assertThat(patient.getMedicationPlan()).isNotNull();
        assertThat(patient.getMedicationPlan().getMedications()).isEmpty();
    }
}