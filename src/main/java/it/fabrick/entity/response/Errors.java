package it.fabrick.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Errors {

    private String code;
    private String description;
    private String params;
}
