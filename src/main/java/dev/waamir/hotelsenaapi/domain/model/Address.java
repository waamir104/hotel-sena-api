package dev.waamir.hotelsenaapi.domain.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @NotEmpty
    private String nomenclature;
    @NotEmpty
    private String numeration;
    @NotEmpty
    private String neighbourhood;
    @NotEmpty
    private String city;
}
