package com.anioncode.witcher;

public class ModelWitcher {
    String Title;
    String NameFile;

    public ModelWitcher(String title, String nameFile) {
        Title = title;
        NameFile = nameFile;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNameFile() {
        return NameFile;
    }

    public void setNameFile(String nameFile) {
        NameFile = nameFile;
    }
}
