package web.petbackend.entity;

import lombok.Data;

@Data
public class PetEncyclopedia {
    // 宠物百科ID
    private Integer pet_ency_id;
    // 品种名称
    private String variety_name;
    // 分类
    private String category;
    // 身体形态
    private String bodily_form;
    // 身高范围
    private String height;
    // 体重范围
    private String weight;
    // 寿命范围
    private String lifetime;
    // 尾部特征
    private String feature_tail;
    // 耳朵特征
    private String feature_ear;
    // 眼睛特征
    private String feature_eye;
    // 毛色
    private String coat_color;
    // 毛长
    private String coat_length;
    // 简介
    private String introduction;
    // 饲养事项
    private String feeding_matters;
    // 图片
    private String image;
}
