package com.vergilyn.examples.testng.entity;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author vergilyn
 * @since 2021-07-30
 */
@Data
@ToString
public class UserEntity {
    private Long id;
    private String username;
}
