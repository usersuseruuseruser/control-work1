package org.example.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@javax.persistence.Entity
@javax.persistence.Table(name = "thread_messages")
public class ThreadMessage {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long threadId;
    private String username;
    private String text;
    private Timestamp data;
    @ManyToOne
    private Thread thread;

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadMessageId) {
        this.threadId = threadMessageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
