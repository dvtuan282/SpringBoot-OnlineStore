package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class orderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "account")
    private AccountEntity account;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreate;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "adress")
    private String adress;
    @Column(name = "status")
    private int status;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetailEntities;

    public orderEntity(int id, AccountEntity account, Date dateCreate, String phoneNumber, String dress, int status) {
        this.id = id;
        this.account = account;
        this.dateCreate = dateCreate;
        this.phoneNumber = phoneNumber;
        this.adress = dress;
        this.status = status;
    }

    @Override
    public String toString() {
        return "orderEntity{" +
                "id=" + id +
                ", account=" + account +
                ", dateCreate=" + dateCreate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dress='" + adress + '\'' +
                ", status=" + status +
                '}';
    }
}
