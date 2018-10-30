package venkov.vladimir.thebeginning.models;

import java.time.Instant;

public class Message {

    private int id;

    private Instant timeSent;

    private boolean isDeleted;

    private Accommodation contextAccommodation;

    private String textOfTheMessage;

    private String imageUrl;

    private User sender;

    private User receiver;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextOfTheMessage() {
        return textOfTheMessage;
    }

    public void setTextOfTheMessage(String textOfTheMessage) {
        this.textOfTheMessage = textOfTheMessage;
    }

    public Instant getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Instant timeSent) {
        this.timeSent = timeSent;
    }

    public Accommodation getContextAccommodation() {
        return contextAccommodation;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setContextAccommodation(Accommodation contextAccommodation) {
        this.contextAccommodation = contextAccommodation;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
