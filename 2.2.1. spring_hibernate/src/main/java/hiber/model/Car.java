package hiber.model;
import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private Integer series;

    @OneToOne(targetEntity = User.class)
    @MapsId
    private User user;

    public Car() {
    }

    public Car(String model, Integer series) {
        this.model = model;
        this.series = series;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
