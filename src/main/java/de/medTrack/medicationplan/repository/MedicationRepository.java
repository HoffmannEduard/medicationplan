package de.medTrack.medicationplan.repository;

import de.medTrack.medicationplan.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
