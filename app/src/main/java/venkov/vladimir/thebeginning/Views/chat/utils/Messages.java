package venkov.vladimir.thebeginning.Views.chat.utils;

public class Messages {

    String message, type, from, imageFrom, imageTo, to;
    Double lat, lon;
    Long time;

    public Messages(String message, Long time, String type, String from) {
        this.message = message;
        this.time = time;
        this.type = type;
        this.from = from;
    }


    //--------------------------------GETTERS AND SETTERS
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Messages() {
    }

    ;

    public String getImageFrom() {
        return imageFrom;
    }

    public void setImageFrom(String imageFrom) {
        this.imageFrom = imageFrom;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getImageTo() {
        return imageTo;
    }

    public void setImageTo(String imageTo) {
        this.imageTo = imageTo;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}

