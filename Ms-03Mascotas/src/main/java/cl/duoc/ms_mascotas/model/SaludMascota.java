package cl.duoc.ms_mascotas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salud_mascota")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información de salud de la mascota")
public class SaludMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salud")
    private Integer idSalud;

    @OneToOne
    @JoinColumn(name = "id_mascota", nullable = false, unique = true)
    private Mascota mascota;

    @Column(name = "vacunada", nullable = false)
    private Boolean vacunada;

    @Column(name = "esterilizada", nullable = false)
    private Boolean esterilizada;

    @Column(name = "desparasitada", nullable = false)
    private Boolean desparasitada;

    @Column(name = "condicion_general", nullable = false)
    private String condicionGeneral;

    @Column(name = "ultima_revision_vet", nullable = false)
    private String ultimaRevisionVet;

    @Column(name = "observaciones", nullable = false)
    private String observaciones;
}