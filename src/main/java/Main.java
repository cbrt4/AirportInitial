import entities.Flight;
import repository.FlightRepository;
import util.DbConnector;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DbConnector dbConnector = DbConnector.getINSTANCE();
        Connection connection = dbConnector.getConnection();
        System.out.println(connection == null ? "NO CONNECTION TO DATABASE" : "CONNECTION ESTABLISHED");
        FlightRepository flightRepository = new FlightRepository(connection);
        //flightRepository.remove(71);
        //flightRepository.add(new Flight(69, "KV-11-11-SF", "Kiev", "San Francisco"));
        //flightRepository.update(new Flight(67, "VI 777", "Vinnitsa", "Napoli"));
        System.out.println(flightRepository.getAll());
    }
}
