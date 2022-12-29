package com.apitest.apitest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class CoinTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer code;

    @Column
    String Time;

    @Column
    String symbol;
    @Column
    Integer rate;
    @Column
    String description;
    @Column
    Integer rate_float;
}
