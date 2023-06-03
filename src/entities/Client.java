package src.entities;

public class Client {
    private String name;
    private int avatarId;
    private int channelId;

    public Client(String name, int avatarId, int channelId) {
        this.avatarId = avatarId;
        this.channelId = channelId;
        this.name = name;
    }

    public Client(int channelId) {
        this.name = "Anônimo " + channelId;
        this.channelId = channelId;
        this.avatarId = 0;
    }

    public Client() {
        this.name = "Anônimo";
        this.channelId = -1;
        this.avatarId = 0;
    }

    public String getName() {
        return name;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setAvatarId(int avatarId) {
        if (avatarId >= 0 && avatarId <= 7) {
            this.avatarId = avatarId;
        }
    }
}
