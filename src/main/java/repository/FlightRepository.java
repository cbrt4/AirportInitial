package repository;

import entities.Flight;
import java.sql.*;
import java.util.*;

public class FlightRepository implements RepositoryInterface {

    private Connection connection;

    public FlightRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> void add(T entity) throws SQLException {

        Flight flightEntity = (Flight) entity;

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO test (flight_number, `from`, `to`) VALUES(?, ?, ?);");
        preparedStatement.setString(1, flightEntity.getFlightNumber());
        preparedStatement.setString(2, flightEntity.getFrom());
        preparedStatement.setString(3, flightEntity.getTo());
        preparedStatement.execute();
    }

    @Override
    public <T> void remove(T entity) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM test WHERE id = ?;");
        preparedStatement.setInt(1, (Integer) entity);
        preparedStatement.execute();
    }

    @Override
    public <T> void update(T entity) throws SQLException {

        Flight flightEntity = (Flight) entity;

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE test SET flight_number = ?, `from` = ?,  `to` = ? WHERE id = ?;");
        preparedStatement.setString(1, flightEntity.getFlightNumber());
        preparedStatement.setString(2, flightEntity.getFrom());
        preparedStatement.setString(3, flightEntity.getTo());
        preparedStatement.setInt(4, flightEntity.getID());

        preparedStatement.execute();
    }

    @Override
    public <T> List<T> getAll() throws SQLException {

        // My method realisation
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM test");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<T> entitiesList = new ArrayList<>();

            while (resultSet.next()) {
                entitiesList.add((T) new Flight(
                        resultSet.getInt("id"),
                        resultSet.getNString("flight_number"),
                        resultSet.getString("from"),
                        resultSet.getString("to")));
            }

        return entitiesList;
    }
}
