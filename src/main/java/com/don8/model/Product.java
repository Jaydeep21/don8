package com.don8.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

/**
 @author BHARAT VAYITLA
 */

@Entity
@Table(name="product", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @Column
   private BigInteger pid;
    @Column
    private BigInteger uid;
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
    private String aid;
    @Column
    private BigInteger price;

}
