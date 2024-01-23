package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        Optional.ofNullable(user.getCar()).ifPresent(this::addCar);
        userDao.add(user);
    }

    @Transactional
    @Override
    public void addCar(Car car) {
        userDao.addCar(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUserAsCar(String model, Integer series) {
        return userDao.getUserAsCar(model, series);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

}
