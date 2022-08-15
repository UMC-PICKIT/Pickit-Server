package com.example.pickit.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String city;
    private String street;
    private String zipcode;

    private int mainAddress=0;

    private String addressStatus = "ACTIVE";

    public void setUser(User user) {
        this.user = user;
        user.getAddressList().add(this);
    }

    public static Address createAddress(User user, String city, String street, String zipcode) {
        Address newAddress = new Address();
        newAddress.setUser(user);
        newAddress.setCity(city);
        newAddress.setStreet(street);
        newAddress.setZipcode(zipcode);

        return newAddress;
    }

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
