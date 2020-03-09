package com.anioncode.witcher;

public class ModelWitcher {
    String imageFlag;
    String NameFile;
    boolean isClicked;

    public ModelWitcher(String imageFlag, String nameFile, boolean isClicked) {
        this.imageFlag = imageFlag;
        NameFile = nameFile;
        this.isClicked = isClicked;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(String imageFlag) {
        this.imageFlag = imageFlag;
    }

    public String getNameFile() {
        return NameFile;
    }

    public void setNameFile(String nameFile) {
        NameFile = nameFile;
    }
}
