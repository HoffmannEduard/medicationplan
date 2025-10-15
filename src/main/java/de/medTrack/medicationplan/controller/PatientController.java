package de.medTrack.medicationplan.controller;

import de.medTrack.medicationplan.model.Patient;
import de.medTrack.medicationplan.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Patienten erstellen
    @PostMapping
    public Patient createPatient(@RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String birthday, //yyyy-MM-dd
                                 @RequestParam Patient.Gender gender) {
        LocalDate date = LocalDate.parse(birthday);
        return patientService.createPatient(name, surname, date, gender);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


}
