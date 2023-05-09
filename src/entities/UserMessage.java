package src.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMessage {
    private final User user;
    private final String content;
    private final Date date;

    public UserMessage(User user, String content, Date date) {
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }

    public String getContent() {
        return this.content;
    }

    public String getFormatedHourString() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(this.date);
    }

    public Date getDate() {
        return this.date;
    }
}
