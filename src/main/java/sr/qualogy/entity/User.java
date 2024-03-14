package sr.qualogy.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import sr.qualogy.entity.Review;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String naam;

    public String email;
    public String phoneNumber;

    public String password;

    public User(Integer id, String naam, String achternaam, String email, String phoneNumber,String password) {
        this.id = id;
        this.naam = naam;

        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String voornaam) {
        this.naam = voornaam;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String extensie) {
        this.email = extensie;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String telefoon) {
        this.phoneNumber = telefoon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
