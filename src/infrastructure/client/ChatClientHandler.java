package src.infrastructure.client;

import java.io.IOException;

import java.util.Vector;
import org.json.JSONObject;

import src.interfaces.InputEventHandler;
import src.interfaces.MemberEventHandler;
import src.interfaces.PointHandler;

import src.entities.Client;

public class ChatClientHandler implements PointHandler<ConnectedServer> {
    private ChatClient chatClient;
    private MemberEventHandler eventActionOnMemberJoin;

    public ChatClientHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public InputEventHandler<ConnectedServer> handleEventOnReceive() {
        return new InputEventHandler<ConnectedServer>() {
            @Override
            public void execute(ConnectedServer connectedClient, String event, JSONObject data) throws IOException {
                switch (event) {
                    case "joined":
                        joinedEvent(connectedClient, data);
                        break;

                    case "new_client":
                        newConnectedMember(connectedClient, data);
                        break;
                }
            }
        };
    }

    private void joinedEvent(ConnectedServer connectedClient, JSONObject data) {
        JSONObject serverData = data.getJSONObject("server");
        String serverName = serverData.getString("name");

        int channelId = data.getInt("channelId");
        Vector<Client> serverMembers = new Vector<Client>();

        // Define all connected server members
        for (Object memberItem : serverData.getJSONArray("connectedClients")) {
            int memberChannelId = ((JSONObject) memberItem).getInt("channelId");
            String memberName = ((JSONObject) memberItem).getString("name");
            int avatarId = ((JSONObject) memberItem).getInt("avatarId");

            Client newMember = new Client(memberName, avatarId, memberChannelId);
            serverMembers.add(newMember);
        }

        this.chatClient.setServerMembers(serverMembers);
        this.chatClient.setServerName(serverName);
        this.chatClient.setChannelId(channelId);
    }

    public void newConnectedMember(ConnectedServer connectedClient, JSONObject data) {
        System.out.println(data.toString(2));
        String memberName = data.getString("name");
        int memberChannelId = data.getInt("channelId");
        int memberAvatarId = data.getInt("avatarId");

        Client newMember = new Client();
        newMember.setName(memberName);
        newMember.setChannelId(memberChannelId);
        newMember.setAvatarId(memberAvatarId);

        this.chatClient.getServerMembers().add(newMember);

        this.eventActionOnMemberJoin.execute(newMember);
    }

    public void setEventActionOnNewMemberJoin(MemberEventHandler eventActionOnMemberJoin) {
        this.eventActionOnMemberJoin = eventActionOnMemberJoin;
    }
}
