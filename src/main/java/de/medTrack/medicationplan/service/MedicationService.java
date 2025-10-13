package de.medTrack.medicationplan.service;

import de.medTrack.medicationplan.model.Medication;
import de.medTrack.medicationplan.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public void saveMedication(Medication medication) {
        medicationRepository.save(medication);
    }

    public Optional<Medication> getMedicament(Long id) {
        return medicationRepository.findById(id);
    }

}
