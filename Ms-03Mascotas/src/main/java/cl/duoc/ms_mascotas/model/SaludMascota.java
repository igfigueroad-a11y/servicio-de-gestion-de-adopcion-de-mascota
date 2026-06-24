package cl.duoc.ms_mascotas.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

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
    @Schema(description = "Identificador único de la salud de la mascota")
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer IdSalud;
        
@Schema(description = "Mascota asociada a la información de salud")
@JoinColumn(name = "id_mascota", nullable = false, unique = true)
private Mascota mascota;

@Schema(description = "Identificador de la mascota")
@Column(name = "id_mascota", nullable = false)
private Integer IdMascota;

@Schema(description = "Indica si la mascota está vacunada")
@Column(name = "vacunada", nullable = false)
private Boolean Vacunada;

@Schema(description = "Indica si la mascota está esterilizada")
@Column(name = "esterilizada", nullable = false)
private Boolean Esterilizada;

@Schema(description = "Indica si la mascota está desparasitada")
@Column(name = "desparasitada", nullable = false)
private Boolean Desparasitada;

@Schema(description = "Condición general de la mascota")
@Column(name = "condicion_general", nullable = false)
private String CondicionGeneral;

@Schema(description = "Última revisión veterinaria")
@Column(name = "ultima_revision_vet", nullable = false)
private String UltimaRevisionVet;

@Schema(description = "Observaciones sobre la salud de la mascota")
@Column(name = "observaciones", nullable = false)
private String Observaciones;

}

