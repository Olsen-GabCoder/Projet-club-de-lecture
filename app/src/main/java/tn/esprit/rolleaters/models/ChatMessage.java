package tn.esprit.rolleaters.models;

public class ChatMessage {
    private String message;
    private String time;
    private boolean isSent; // true pour les messages envoyés, false pour les reçus

    public ChatMessage(String message, String time, boolean isSent) {
        this.message = message;
        this.time = time;
        this.isSent = isSent;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public boolean isSent() {
        return isSent;
    }
}
