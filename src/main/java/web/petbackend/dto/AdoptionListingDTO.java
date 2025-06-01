package web.petbackend.dto;
import lombok.Data;
import web.petbackend.entity.Pet;
import java.time.LocalDateTime;

@Data
public class AdoptionListingDTO {
    private Integer adoptionId;
    private Integer petId;
    private Integer listedBy;
    private String description;
    private String status;
    private LocalDateTime listedAt;
    private LocalDateTime adoptedAt;
    private String image;
    private String petName;
    private String petBreed;
    private String petGender;
    private String petCity;
}
