package web.petbackend.entity;

import lombok.Data;

@Data
public class HealthRecord {
    private Integer recordId;
    private Integer petId;
    private String type;
    private String description;
    private String recordDate;
} 