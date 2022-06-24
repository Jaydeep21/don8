package com.don8.model;


import com.don8.model.dbentity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

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
    @NotBlank(message = "Name is mandatory")
    private String productName;
    @Column
    private Date dateAdded;
    @Column
    @NotBlank(message = "expiry date is mandatory")
    private  Date dateExpiry;

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    //    @Column(length = 64)
//    @NotBlank(message = "product photo is mandatory")
//    private String photos;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
              @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
               @JoinColumn(name = "image_id")
            })
    @NotBlank(message = "upload image")
    private Set<Image> image;

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
