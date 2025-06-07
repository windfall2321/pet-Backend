package web.petbackend.entity;

import lombok.Data;

@Data
public class Pet {
    private int petId;
    private String name;
    private int ownerId;
    private String breed;
    private String gender;
    private String city;
    private String image;

}
