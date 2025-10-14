package de.medTrack.medicationplan.service;

import de.medTrack.medicationplan.model.Patient;
import de.medTrack.medicationplan.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {
   private final PatientRepository patientRepository;

   @Autowired
   public PatientService(PatientRepository patientRepository) {
       this.patientRepository = patientRepository;
   }

   public Patient createPatient(String name, String surname, LocalDate birthday, Patient.Gender gender) {
       Patient patient = Patient.builder()
               .name(name)
               .surname(surname)
               .birthday(birthday)
               .gender(gender)
               .build();

       patient.initializeMedicationPlan();
       return patientRepository.save(patient);
   }

   public List<Patient> getAllPatients() {
       return patientRepository.findAll();
   }



}
