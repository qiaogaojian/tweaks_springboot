package com.mega.tweaks.model.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "test_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestUser
{
    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 邮件
     */
    @Column(name = "email")
    private String email;
}