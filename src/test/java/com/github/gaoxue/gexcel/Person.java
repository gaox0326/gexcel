package com.github.gaoxue.gexcel;

import java.util.Date;

import com.github.gaoxue.gexcel.template.Column;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gaoxue
 */
public class Person {

    @Getter
    @Setter
    @Column
    private Long id;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column(name = "mobile")
    private String mobilePhoneNumber;

    // TODO association
    // @Getter
    // @Setter
    // @Column(name = "department")
    // private Long departmentId;

    @Getter
    @Setter
    @Column
    private Integer age;

    @Getter
    @Setter
    @Column
    private Date birthdate;

    @Getter
    @Setter
    @Column
    private Boolean isEnable;

    @Getter
    @Setter
    @Column(name = "Byte")
    private Byte byteCol;

    @Getter
    @Setter
    @Column(name = "Short")
    private Short shortCol;

    @Getter
    @Setter
    @Column(name = "Integer")
    private Integer intCol;

    @Getter
    @Setter
    @Column(name = "Long")
    private Long longCol;

    @Getter
    @Setter
    @Column(name = "Character")
    private Character charCol;

    @Getter
    @Setter
    @Column(name = "Double")
    private Double doubleCol;

    @Getter
    @Setter
    @Column(name = "Float")
    private Float floatCol;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id: " + id).append(", ");
        result.append("name: " + name).append(", ");
        result.append("mobilePhoneNumber: " + mobilePhoneNumber).append(", ");
        result.append("age: " + age).append(", ");
        result.append("birthdate: " + birthdate).append(", ");
        result.append("isEnable: " + isEnable).append(", ");
        
        result.append("byteCol: " + byteCol).append(", ");
        result.append("shortCol: " + shortCol).append(", ");
        result.append("intCol: " + intCol).append(", ");
        result.append("longCol: " + longCol).append(", ");
        result.append("charCol: " + charCol).append(", ");
        result.append("doubleCol: " + doubleCol).append(", ");
        result.append("floatCol: " + floatCol).append(", ");
        return result.toString();
    }
}
