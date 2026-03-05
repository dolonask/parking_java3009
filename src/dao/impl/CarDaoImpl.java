package dao.impl;

import dao.CarDao;
import dao.DbHelper;
import model.Car;

import java.sql.*;

public class CarDaoImpl implements CarDao {
    @Override
    public Car findByCarNumber(String carNumber) {
        Connection connection = DbHelper.INSTANCE.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select id, car_number from cars where car_number = ?;");
            ps.setString(1, carNumber);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt(1));
                car.setCarNumber(resultSet.getString(2));
                return car;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Car saveCar(String carNumber) {
        Connection connection = DbHelper.INSTANCE.getConnection();
        String query = "insert into cars(car_number) values(?);";
        Car car = new Car();
        car.setCarNumber(carNumber);
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carNumber);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()){
                car.setId(generatedKeys.getInt(1));
            }

            connection.close();
            ps.close();
            return car;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
