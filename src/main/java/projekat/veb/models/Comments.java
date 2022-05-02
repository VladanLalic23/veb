package projekat.veb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurants_id")
    private Restaurants restaurants;

    @Column
    private String comment;

    @Column
    private Long mark;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return this.customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Restaurants getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(Restaurants restaurants) {
        this.restaurants = restaurants;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getMark() {
        return this.mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", customer='" + getCustomer() + "'" +
                ", restaurants='" + getRestaurants() + "'" +
                ", comment='" + getComment() + "'" +
                ", mark='" + getMark() + "'" +
                "}";
    }

}
