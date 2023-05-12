package src.entities;

public class Client {
    protected String name;
    protected int avatarId;
    private int channelId;
    
    public Client(String name, int avatarId, int channelId) {
        this.avatarId = avatarId;
        this.channelId = channelId;
        this.name = name;
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

    protected void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    protected int getChannelId() {
        return channelId;
    }

    public void setAvatarId(int avatarId) {
        if (avatarId >= 0 && avatarId <= 7) {
            this.avatarId = avatarId;
        }
    }
}
