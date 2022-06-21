package com.don8.model.dbentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @Size(max = 100)
    @NotBlank(message = "Address Line 1 is mandatory")
    private String line1;
    @Size(max = 100)
    @NotBlank(message = "Address line 2 is mandatory")
    private String line2;
    @Column(name = "pincode")
    @Size(max = 100)
    @NotBlank(message = "PinCode is mandatory")
    private String pinCode;
    @Size(max = 100)
    @NotBlank(message = "City is mandatory")
    private String city;
    @Size(max = 100)
    @NotBlank(message = "State/Province is mandatory")
    private String state;
    @Size(max = 100)
    @NotBlank(message = "Country Name is mandatory")
    private String country;
    @Column(name = "gcode")
    @Size(max = 100)
    private String gcode;
    @Column(name = "longitude_latitude")
    @Size(max = 100)
    private String longitudeLatitude;
}
