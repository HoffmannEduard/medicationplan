package de.medTrack.medicationplan.repository;

import de.medTrack.medicationplan.model.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Long> {
}
