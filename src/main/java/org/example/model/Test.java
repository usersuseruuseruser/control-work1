package org.example.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@javax.persistence.Table(name = "test")
public class Test {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long testId;
    private String title;
    private String imageUrl;
    @OneToMany
    private List<Question> questions;

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
