package de.medTrack.medicationplan.service;


import de.medTrack.medicationplan.model.Medication;
import de.medTrack.medicationplan.model.MedicationPlan;
import de.medTrack.medicationplan.model.Patient;
import de.medTrack.medicationplan.repository.MedicationPlanRepository;
import de.medTrack.medicationplan.repository.MedicationRepository;
import de.medTrack.medicationplan.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationPlanService {
    public final MedicationPlanRepository medicationPlanRepository;
    public final MedicationRepository medicationRepository;
    public final PatientRepository patientRepository;

    @Autowired
    public MedicationPlanService(
            MedicationPlanRepository medicationPlanRepository,
            MedicationRepository medicationRepository,
            PatientRepository patientRepository
    ) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
    }

    public MedicationPlan addMedicationToPatientPlan(Long patientId, Medication medication) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        MedicationPlan plan = patient.getMedicationPlan();
        plan.getMedications().add(medication);

        return medicationPlanRepository.save(plan);
    }

}
