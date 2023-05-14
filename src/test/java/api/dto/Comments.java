package api.dto;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/10/2023
 * #Comments:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comments {
    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;
}