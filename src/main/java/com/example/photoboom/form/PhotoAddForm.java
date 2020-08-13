package com.example.photoboom.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class PhotoAddForm {

    @NotEmpty
    @Size(min = 2, max = 50)
    private String title;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
