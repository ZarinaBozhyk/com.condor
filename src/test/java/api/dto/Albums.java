package api.dto;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/11/2023
 * #Comments:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Albums {
    private int userId;
    private int id;
    private String title;
}