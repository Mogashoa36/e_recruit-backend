package recruit.recruit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recruit.recruit.entity.Vacancy;
import recruit.recruit.service.VacancyService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/vacancy")
@RestController
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    // Get all vacancies
    @GetMapping("/getAll")
    public List<Vacancy> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }

    // Get vacancy by ID
    @GetMapping("/getBy/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long id) {
        Optional<Vacancy> vacancy = vacancyService.getVacancyById(id);
        return vacancy.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new vacancy
    @PostMapping("/capture")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
        Vacancy newVacancy = vacancyService.createVacancy(vacancy);

        String status = "Open";

       vacancy.setStatus(status);

        return ResponseEntity.ok(newVacancy);
    }

    // Update an existing vacancy
    @PutMapping("/update/{id}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable Long id, @RequestBody Vacancy vacancy) {
        try {
            Vacancy updatedVacancy = vacancyService.updateVacancy(id, vacancy);
            return ResponseEntity.ok(updatedVacancy);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a vacancy
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }

}
