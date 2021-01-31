package com.hust.dces.Entity;

public class SensitiveWord {
    private String sensitiveword;
    private int wordtypeid;
    private int itemid;
    private String wordtype;

    public String getSensitiveword() {
        return sensitiveword;
    }

    public void setSensitiveword(String sensitiveword) {
        this.sensitiveword = sensitiveword;
    }

    public int getWordtypeid() {
        return wordtypeid;
    }

    public void setWordtypeid(int wordtypeid) {
        this.wordtypeid = wordtypeid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getWordtype() {
        return wordtype;
    }

    public void setWordtype(String wordtype) {
        this.wordtype = wordtype;
    }
}