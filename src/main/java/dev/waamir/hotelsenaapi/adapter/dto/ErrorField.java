package dev.waamir.hotelsenaapi.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorField {
    
    private String fieldName;
    private String code;
    private Object rejectedValue;
}
