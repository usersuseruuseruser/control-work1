package org.example.model;

import javax.persistence.OneToMany;
import java.util.Set;

@javax.persistence.Entity
@javax.persistence.Table(name = "threads")
public class Thread {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long threadId;
    private String title;
    private String description;
    private String imageUrl;
    @OneToMany(mappedBy = "thread")
    private Set<ThreadMessage> threadMessages;

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long forum_thread_id) {
        this.threadId = forum_thread_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }
}
