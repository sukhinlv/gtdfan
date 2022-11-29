package ru.jsft.gtdfan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// NoArgs and AllArgs need for Mapstruct to correctly map id field and other fields of superclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class AbstractDto implements Serializable {

    private Long id;
}
