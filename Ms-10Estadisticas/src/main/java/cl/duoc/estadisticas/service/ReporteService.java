package cl.duoc.estadisticas.service;
import cl.duoc.estadisticas.model.Reporte;
import cl.duoc.estadisticas.repository.ReporteRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> listarReportes() {
        return reporteRepository.findAll();
    }

    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte buscarReportePorId(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    public void eliminarReporte(Integer id) {
        reporteRepository.deleteById(id);
    }
}
