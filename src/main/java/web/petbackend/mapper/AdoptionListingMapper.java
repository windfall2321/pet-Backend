package web.petbackend.mapper;

import web.petbackend.entity.AdoptionListing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptionListingMapper {

    int insert(AdoptionListing listing);

    AdoptionListing selectById(@Param("adoptionId") Integer adoptionId);
    List<AdoptionListing> selectByUserId(@Param("listedBy") Integer listedBy);

    List<AdoptionListing> selectAll();

    int update(AdoptionListing listing);

    int deleteById(@Param("adoptionId") Integer adoptionId);

    List<AdoptionListing> searchByPetName(@Param("petName") String petName);
}
