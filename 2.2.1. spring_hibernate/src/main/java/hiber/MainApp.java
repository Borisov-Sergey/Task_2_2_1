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
        Car carOne = new Car("Car1", 1);
        Car carTree = new Car("Car3", 3);
        userService.addCar(carOne);
        userService.addCar(new Car("Car2", 2));
        userService.addCar(carTree);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        User userWithCarOne = new User("User5", "Lastname5", "user5@mail.ru");
        userWithCarOne.setCar(carOne);
        userService.add(userWithCarOne);
        User userWithCarTwo = new User("User6", "Lastname6", "user6@mail.ru");
        userWithCarTwo.setCar(carTree);
        userService.add(userWithCarTwo);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(("Car = " + user.getCar()));
            System.out.println();
        }

        context.close();
    }
}
