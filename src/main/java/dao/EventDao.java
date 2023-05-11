package dao;

import jdk.jshell.spi.ExecutionControl;
import org.example.Event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EventDao extends baseDAO<Event>{

    public EventDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Event element) throws ExecutionControl.NotImplementedException, SQLException {
        return false;
    }

    @Override
    public boolean delete(Event element) throws ExecutionControl.NotImplementedException, SQLException {
        return false;
    }

    @Override
    public Event getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        return null;
    }

    @Override
    public List<Event> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        return null;
    }

    @Override
    public boolean update(Event element) throws ExecutionControl.NotImplementedException, SQLException {
        return false;
    }
}
