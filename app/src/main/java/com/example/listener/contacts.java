package com.example.listener;

public class contacts {
    private int id;
    private String name;
    public contacts(){

    }
    public contacts(int id,String name,String phn,String mail_add){
        this.id=id;
        this.name=name;
        this.phn=phn;
        this.mail_add=mail_add;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getMail_add() {
        return mail_add;
    }

    public void setMail_add(String mail_add) {
        this.mail_add = mail_add;
    }

    private String phn;
    private String mail_add;


}
