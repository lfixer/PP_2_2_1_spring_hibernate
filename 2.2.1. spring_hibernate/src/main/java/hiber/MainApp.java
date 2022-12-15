package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("car1", 1111);
        car1.setUser(user1);
        Car car2 = new Car("car2", 2222);
        car2.setUser(user2);
        Car car3 = new Car("car3", 3333);
        car3.setUser(user3);
        Car car4 = new Car("car4", 4444);
        car4.setUser(user4);

        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            Long id = user.getId();
            System.out.println("Id = " +id);
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(carService.getById(id));
            System.out.println();
        }

        System.out.println(userService.getUserByCar("car1", 1111).getFirstName());

        context.close();
    }
}
