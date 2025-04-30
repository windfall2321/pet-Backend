package web.petbackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdoptionApplication {
    private Integer adoptionApplicationId;
    private Integer adoptionId;
    private Integer applicantId;
    private String reason;
    private String status;
    private LocalDateTime listedAt;
    private LocalDateTime adoptedAt;
    private Integer reviewedBy;
}
