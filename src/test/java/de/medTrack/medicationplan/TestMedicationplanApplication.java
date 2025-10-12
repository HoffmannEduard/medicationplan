package de.medTrack.medicationplan;

import org.springframework.boot.SpringApplication;

public class TestMedicationplanApplication {

	public static void main(String[] args) {
		SpringApplication.from(MedicationplanApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
