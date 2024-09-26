package recruit.recruit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recruit.recruit.dao.VacancyDao;
import recruit.recruit.entity.Vacancy;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService  {

    @Autowired
    private VacancyDao vacancyDao;


    public List<Vacancy> getAllVacancies() {
        return vacancyDao.findAll();
    }


    public Optional<Vacancy> getVacancyById(Long id) {
        return vacancyDao.findById(id);
    }


    public Vacancy createVacancy(Vacancy vacancy) {
        return vacancyDao.save(vacancy);
    }


    public Vacancy updateVacancy(Long id, Vacancy vacancy) {
        return vacancyDao.findById(id).map(existingVacancy -> {
            existingVacancy.setJobTitle(vacancy.getJobTitle());
            existingVacancy.setCompanyName(vacancy.getCompanyName());
            existingVacancy.setLocation(vacancy.getLocation());
            existingVacancy.setJobType(vacancy.getJobType());
            existingVacancy.setDescription(vacancy.getDescription());
            existingVacancy.setSalary(vacancy.getSalary());
            existingVacancy.setDeadline(vacancy.getDeadline());
            existingVacancy.setRequirements(vacancy.getRequirements());
            return vacancyDao.save(existingVacancy);
        }).orElseThrow(() -> new RuntimeException("Vacancy not found"));
    }


    public void deleteVacancy(Long id) {
        vacancyDao.deleteById(id);
    }
}
