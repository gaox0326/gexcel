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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id: " + id).append(", ");
        result.append("name: " + name).append(", ");
        result.append("mobilePhoneNumber: " + mobilePhoneNumber).append(", ");
        result.append("age: " + age).append(", ");
        result.append("birthdate: " + birthdate);
        return result.toString();
    }
}
