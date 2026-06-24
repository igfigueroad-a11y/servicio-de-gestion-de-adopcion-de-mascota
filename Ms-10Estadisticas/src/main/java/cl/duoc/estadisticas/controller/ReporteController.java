package cl.duoc.estadisticas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import cl.duoc.estadisticas.model.Reporte;
import cl.duoc.estadisticas.service.ReporteService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes() {
        List<Reporte> listaReportes = reporteService.listarReportes();
        if (listaReportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaReportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscarReportePorId(@PathVariable Integer id) {
        try {
            Reporte reporte = reporteService.buscarReportePorId(id);
            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

  @PostMapping
public ResponseEntity<Reporte> guardarReporte(@RequestBody Reporte reporte) {
    Reporte nuevoReporte = reporteService.guardarReporte(reporte);
    return ResponseEntity.ok(nuevoReporte);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarReporte(@PathVariable Integer id) {
    try {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
}
