package com.disi.controller;

import com.disi.dto.MedicationDTO;
import com.disi.dto.MedicationPlanDTO;
import com.disi.models.Medication;
import com.disi.models.MedicationPlan;
import com.disi.repository.MedicationRepository;
import com.disi.service.MedicationPlanService;
import com.disi.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private MedicationPlanService medicationPlanService;

    @PostMapping("/new")
    public MedicationDTO addMedication(@RequestBody MedicationDTO medDTO){
        Medication  medication = new ModelMapper().map(medDTO, Medication.class);
        return new ModelMapper().map(medicationService.addMedication(medication), MedicationDTO.class);
    }
    //CREATE MEDPLAN
    @PostMapping("/medplan")
    public MedicationPlanDTO addMedPlan(@RequestBody MedicationPlanDTO medplan){
        return medicationPlanService.add(medplan);
    }

    @GetMapping("/all")
    public List<MedicationDTO> getAllMedications(){
        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        List<Medication> list = medicationService.getAllMedication();
        for (Medication med : list) {
            medicationDTOS.add(new ModelMapper().map(med, MedicationDTO.class));
        }
        return  medicationDTOS;
    }

    @GetMapping("/medplan")
    public List<MedicationPlanDTO> getAll(){
        List<MedicationPlanDTO> medplans = medicationPlanService.getAll();
        return medplans;
    }

    @GetMapping()
    public Medication getMedicationById(@RequestParam int id){
        return medicationService.getMedication(id);
    }

    @PutMapping("/update")
    public Medication updateMedicationById(@RequestParam int id, @RequestBody Medication medication){
        return medicationService.editMedication(id, medication);
    }

    @DeleteMapping
    public int deleteMedicationById(@RequestParam int id){
        return medicationService.deleteMedication(id);
    }

    @GetMapping("/getAllByPatientId")
    public List<MedicationPlanDTO> getAllByPatientId(@RequestParam int id){
        return medicationPlanService.findAllByPatientId(id);
    }

}
