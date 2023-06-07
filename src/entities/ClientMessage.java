package src.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientMessage {
    private final Client user;
    private final String content;
    private final int targetChannelId;
    private final Date date;

    public ClientMessage(Client user, int targetChannelId, String content, Date date) {
        this.user = user;
        this.targetChannelId = targetChannelId;
        this.content = content;
        this.date = date;
    }

    public int getTargetChannelId() {
        return this.targetChannelId;
    }

    public Client getUser() {
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
