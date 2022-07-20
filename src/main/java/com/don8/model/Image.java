//package com.don8.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
///**
// @author BHARAT VAYITLA
// */
//
//
//@Entity
//@Table(name ="image")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class Image {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//    private String name;
//    private String type;
//    @Column(length = 50000000)
//    private byte[] photoByte;
//
//    public Image(String name, String type, byte[] photoByte) {
//        this.name = name;
//        this.type = type;
//        this.photoByte = photoByte;
//    }
//}
