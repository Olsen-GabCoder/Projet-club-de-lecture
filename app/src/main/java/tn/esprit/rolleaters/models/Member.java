package tn.esprit.rolleaters.models;

public class Member {
    private String name;
    private String lastMessage;
    private String hour;
    private int avatarResId;

    public Member(String name, String lastMessage, String hour, int avatarResId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.hour = hour;
        this.avatarResId = avatarResId;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getHour() {
        return hour;
    }

    public int getAvatarResId() {
        return avatarResId;
    }
}
