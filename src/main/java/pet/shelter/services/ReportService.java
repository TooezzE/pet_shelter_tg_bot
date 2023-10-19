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
 * Класс для работы с сущностями отчетов
 **/
@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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
        reportRepository.save(report);
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Data not found"));
    }

    public Report findByChatId(Long chatId) {
        return reportRepository.findByChatId(chatId);
    }

    public Collection<Report> findSetByChatId(Long chatId) {
        return reportRepository.findSetByChatId(chatId);
    }

    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

}
