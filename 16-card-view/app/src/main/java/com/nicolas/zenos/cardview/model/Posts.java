package com.nicolas.zenos.cardview.model;

public class Posts {
    private String name;
    private String post;
    private int image;

    public Posts(){

    }

    public Posts(String name, String post, int image) {
        this.name = name;
        this.post = post;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
