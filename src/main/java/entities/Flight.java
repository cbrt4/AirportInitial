package entities;

public class Flight { // POJO

    private Integer ID;
    private String flightNumber;
    private String from;
    private String to;

    public Flight(Integer ID, String flightNumber, String from, String to) {
        this.ID = ID;
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
    }

    public Flight(Integer ID) {
        this.ID = ID;
    }

    public Flight() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "\nFlight ID: " + getID() +
                "\nFlight number: " + getFlightNumber() +
                "\nFrom " + getFrom() + " to " + getTo() +
                "\n";
    }
}
