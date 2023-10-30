package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.exceptions.ReportNotFoundException;
import pet.shelter.model.Report;
import pet.shelter.repository.ReportRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Reports service class
 **/
@Service
@Transactional
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public void loadReport(Long chatId, String name, String nutrition
            , String health, String behaviour, Date lastMessage, byte[] dataFile) throws IOException {
        Report report = new Report();
        report.setChatId(chatId);
        report.setName(name);
        report.setNutrition(nutrition);
        report.setHealth(health);
        report.setBehaviour(behaviour);
        report.setLastMessage(lastMessage);
        report.setData(dataFile);
        this.repository.save(report);
    }

    public Report save(Report report) {
        return repository.save(report);
    }

    public Report findById(Long id) {
        return repository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }

    public Report findByChatId(Long chatId) {
        return repository.findByChatId(chatId);
    }

    public Collection<Report> findSetByChatId(Long chatId) {
        return repository.findSetByChatId(chatId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Report> getAll() {
        return repository.findAll();
    }

}
