package com.example.hl.tz.entities;

import org.hibernate.id.UUIDGenerator;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bank")
    private List<Credit> credits;

    @OneToMany(mappedBy = "bank" ,cascade = CascadeType.ALL)
    private List<Client> clients;

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public Bank() {
    }

    public Bank(List<Credit> credits, List<Client> clients) {
        this.credits = credits;
        this.clients = clients;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", credits=" + credits +
                ", clients=" + clients +
                '}';
    }
}
