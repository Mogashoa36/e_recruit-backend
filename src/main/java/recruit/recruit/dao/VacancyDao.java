package recruit.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recruit.recruit.entity.Vacancy;

@Repository
public interface VacancyDao extends JpaRepository<Vacancy, Long> {
}
