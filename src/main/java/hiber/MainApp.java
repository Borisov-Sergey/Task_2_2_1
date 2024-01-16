package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Car1", 1);
        Car car2 = new Car("Car2", 2);
        Car car3 = new Car("Car3", 3);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user1.setCar(car1);
        userService.add(user1);

        user2.setCar(car2);
        userService.add(user2);

        user3.setCar(car3);
        userService.add(user3);

        userService.add(user4);

        List<User> usersAsCar = userService.getUserAsCar("Car1", 1);
        System.out.println("Вывод пользователей с определенным автомобилем: ");
        for (User user : usersAsCar) {
            System.out.println(user);
        }
        System.out.println("Вывод всех пользователей");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        context.close();
    }
}
