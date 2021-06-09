package it.fabrick.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Errors {
    private String code;
    private String description;
    private String params;
}
