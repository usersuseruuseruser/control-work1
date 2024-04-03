package org.example.model;

@javax.persistence.Entity
@javax.persistence.Table(name = "threads_messages")
public class ThreadsMessages {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long threadsMessagesId;
    private Long threadId;
    private Long threadMessageId;

    public ThreadsMessages(long threadsMessagesId, long threadId, long threadMessageId) {
        this.threadsMessagesId = threadsMessagesId;
        this.threadId = threadId;
        this.threadMessageId = threadMessageId;
    }

    public ThreadsMessages() {
    }

    public long getThreadsMessagesId() {
        return threadsMessagesId;
    }

    public void setThreadsMessagesId(long threadsMessagesId) {
        this.threadsMessagesId = threadsMessagesId;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getThreadMessageId() {
        return threadMessageId;
    }

    public void setThreadMessageId(long threadMessageId) {
        this.threadMessageId = threadMessageId;
    }
}
