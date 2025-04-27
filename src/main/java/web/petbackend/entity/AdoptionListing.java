package web.petbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdoptionListing {
    private Integer adoptionId;
    private Integer petId;
    private Integer listedBy;
    private String description;
    private String status;
    private LocalDateTime listedAt;
    private LocalDateTime adoptedAt;
}
