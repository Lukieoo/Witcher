package com.anioncode.witcher;

public class ModelWitcher {
    String imageFlag;
    String NameFile;

    public ModelWitcher(String imageFlag, String nameFile) {
        this.imageFlag = imageFlag;
        NameFile = nameFile;
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
