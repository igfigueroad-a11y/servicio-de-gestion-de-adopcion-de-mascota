package cl.duoc.ms_mascotas.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "mascotas")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "Información de la mascota")
public class Mascota {

@Schema(description = "Identificador único de la mascota")    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdMascota;

@Schema(description = "Nombre de la mascota")
@Column(name = "nombre", nullable = false)
    private String NombreMascota;
@Schema(description = "Identificador del tipo de mascota")
@Column(name = "id_tipo_mascota", nullable = false)
        private Integer IdTipoMascota;
@Schema(description = "Especie de la mascota")
@Column(name = "especie", nullable = false)
    private String  EspecieMascota;

@Schema(description = "Raza de la mascota")
@Column(name = "raza", nullable = false)
    private String Raza;

@Schema(description = "Edad de la mascota")
@Column(name = "edad", nullable = false)
    private Integer Edad;

@Schema(description = "Descripción de la mascota")
@Column(name = "descripcion", nullable = false)
    private String Descripcion;
    
@Schema(description = "Disponibilidad de la mascota")
@Column(name = "disponibilidad", nullable = false)
    private String Disponibilidad;

@Schema(description = "Fecha de ingreso de la mascota")
@Column(name = "fecha_ingreso", nullable = false)
    private LocalDate FechaIngreso;

    @Schema(description = "Información de salud de la mascota") 
@OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL)
private SaludMascota saludMascota;
    }

