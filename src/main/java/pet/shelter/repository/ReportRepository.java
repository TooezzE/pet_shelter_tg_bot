package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.Report;

import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByChatId(Long chatId);

    Set<Report> findSetByChatId(Long chatId);
}
