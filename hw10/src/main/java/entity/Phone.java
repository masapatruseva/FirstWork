package entity;


import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Phone(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
