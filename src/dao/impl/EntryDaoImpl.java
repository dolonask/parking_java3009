package dao.impl;

import dao.DbHelper;
import dao.EntryDao;
import model.Entry;
import model.enums.EntryStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntryDaoImpl implements EntryDao {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    @Override
    public void saveEntry(Entry entry) {
        Connection connection = DbHelper.INSTANCE.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into entries(id_cars,start_date,status) values(?,?,?);");
            ps.setInt(1, entry.getCarId());
            ps.setString(2, entry.getStartDate().format(formatter));
            ps.setString(3, entry.getStatus().name());

            ps.executeUpdate();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCarInside(Integer carId) {
        Connection connection = DbHelper.INSTANCE.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select count(*) from entries where id_cars = ? and status = ?;");
            ps.setInt(1, carId);
            ps.setString(2, EntryStatus.IN.name());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                int count = resultSet.getInt(1);
                return count != 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    @Override
    public Entry findByCarIdAndStatus(Integer carId, EntryStatus status) {
        Connection connection = DbHelper.INSTANCE.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, start_date from entries where id_cars = ? and status = ?;");
            preparedStatement.setInt(1, carId);
            preparedStatement.setString(2, status.name());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Entry entry = new Entry();
                entry.setId(resultSet.getInt("id"));
                entry.setStartDate(LocalDateTime.parse(resultSet.getString("start_date"), formatter));
                entry.setCarId(carId);
                entry.setStatus(status);

                preparedStatement.close();
                resultSet.close();
                connection.close();


                return entry;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void setEntry(Entry entry) {
        Connection connection = DbHelper.INSTANCE.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update entries set status = ?, end_date = ?, cost = ? where id = ?;");
            preparedStatement.setString(1, entry.getStatus().name());
            preparedStatement.setString(2, formatter.format(entry.getEndDate()));
            preparedStatement.setDouble(3, entry.getCost());
            preparedStatement.setInt(4, entry.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
