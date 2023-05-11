package dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.Place;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PlaceDao extends baseDAO<Place>{
    public PlaceDao(Connection connection) {
        super(connection);
    }

    public boolean save(Place element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO place (name, address, capacity) VALUES (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setString(2, element.getAddress());
        statement.setInt(3, element.getCapacity());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) {
            element.setId(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    @Override
    public boolean delete(Place element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM place where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, element.getId());
        int rowNb =statement.executeUpdate();
        return rowNb > 0;
    }

    @Override
    public Place getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Place place = null;
        request = "SELECT * FROM place WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            place = new Place(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("address"),resultSet.getInt("capacity"));
        }
        return place;
    }

    @Override
    public List<Place> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        return null;
    }

    @Override
    public boolean update(Place place) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE place set name = ?, address = ?, capacity = ? WHERE id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, place.getName());
        statement.setString(2, place.getAddress());
        statement.setInt(3, place.getCapacity());
        statement.setInt(4, place.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }
}
