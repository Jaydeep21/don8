package com.don8.model.dbentity;


import com.don8.model.dbentity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Product extends AuditModel{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long pid;
    @Column
    private Long uid;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String productName;
    @Column
    @NotNull(message = "expiry date is mandatory")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy/MM/dd")
    private Date dateAdded;
    @Column
    @NotNull(message = "expiry date is mandatory")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy/MM/dd")
    private  Date dateExpiry;

    private String product_image_type;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "product_image", columnDefinition="BLOB")
    @JsonIgnore
    private byte[] product_image;

    @Column
    private String description;
    @Column
    private Long aid;
    @Column
    private BigInteger price;
    @Column
    private Boolean is_donated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName = "uid",insertable = false, updatable = false)
    User user;


}
