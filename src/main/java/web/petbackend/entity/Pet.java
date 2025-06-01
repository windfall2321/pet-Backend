package web.petbackend.entity;

import lombok.Data;

@Data
public class Pet {
    private int petId;
    private String Name;
    private int ownerId;
    private String Breed;
    private String Gender;
    private String city;
    private String image;

}
