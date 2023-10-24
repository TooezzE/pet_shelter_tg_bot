package pet.shelter.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.shelter.model.Report;
import pet.shelter.services.ReportService;

import java.util.Collection;

@RestController
@RequestMapping("file")
public class ReportController {

    private final ReportService reportService;
    private final String fileType = "image/jpeg";

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> findReport(@Parameter(description = "report id") @PathVariable Long id) {
        Report report = reportService.findById(id);
        if (report != null) {
            return ResponseEntity.ok(report);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@Parameter(description = "report id") @PathVariable Long id) {
        reportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Report>> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> findPhotoFromDB(@Parameter(description = "report id") @PathVariable Long id) {
        Report report = reportService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(report.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(report.getData());
    }
}
