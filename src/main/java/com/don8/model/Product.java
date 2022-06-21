package com.don8.model;


import com.don8.model.dbentity.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 @author BHARAT VAYITLA
 */

@Entity
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long pid;
    @Column
    private Long uid;
    @Column
    private String productName;
    @Column
    private Date dateAdded;
    @Column
    private  Date dateExpiry;
    @Column(length = 64)
    private String photos;
    @Column
    private String description;
    @Column
    private Long aid;
    @Column
    private BigInteger price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName = "uid",insertable = false, updatable = false)
    User user;
}
