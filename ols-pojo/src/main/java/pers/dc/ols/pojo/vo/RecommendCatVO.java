package pers.dc.ols.pojo.vo;

import java.util.List;

public class RecommendCatVO {

    private String rootCatName;
    private String slogan;
    private String bgColor;
    private String catImage;
    private List<RecommendCatVO> simpleItemList;

    public String getRootCatName() {
        return rootCatName;
    }

    public void setRootCatName(String rootCatName) {
        this.rootCatName = rootCatName;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public List<RecommendCatVO> getSimpleItemList() {
        return simpleItemList;
    }

    public void setSimpleItemList(List<RecommendCatVO> simpleItemList) {
        this.simpleItemList = simpleItemList;
    }
}
