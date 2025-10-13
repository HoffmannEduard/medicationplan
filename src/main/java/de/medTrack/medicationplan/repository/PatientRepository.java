package de.medTrack.medicationplan.repository;

import de.medTrack.medicationplan.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
