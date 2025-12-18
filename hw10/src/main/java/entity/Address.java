package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    @JoinColumn(name = "user_id")
    @OneToOne(mappedBy = "address")
    private User user;

    public Address(String address, User user) {
        this.street = address;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return street;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.street = address;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
