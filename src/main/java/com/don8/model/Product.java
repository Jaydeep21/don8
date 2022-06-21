package com.don8.model;


import com.don8.model.dbentity.AuditModel;
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
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product extends AuditModel {
 @Id
 @Column
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
 private String aid;
 @Column
 private BigInteger price;
}
