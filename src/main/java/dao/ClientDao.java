package dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.Client;
import org.example.Place;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientDao extends baseDAO<Client>{

    public ClientDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Client client) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO client (last_name, first_name, email) VALUES (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, client.getLastName());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getEmail());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) {
            client.setId(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    @Override
    public boolean delete(Client element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM client where id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, element.getId());
        int rowNb =statement.executeUpdate();
        return rowNb > 0;
    }

    @Override
    public Client getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Client client = null;
        request = "SELECT * FROM client WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            client = new Client(resultSet.getInt("id"), resultSet.getString("last_name"),resultSet.getString("first_name"),resultSet.getString("email"));
        }
        return client;
    }

    @Override
    public List<Client> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        return null;
    }

    @Override
    public boolean update(Client element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE client set last_name = ?, first_name = ?, email = ? WHERE id = ?";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getLastName());
        statement.setString(2, element.getFirstName());
        statement.setString(3, element.getEmail());
        statement.setInt(4, element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }
}
