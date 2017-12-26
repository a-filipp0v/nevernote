package com.nvn.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"noteholders"})
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Column(name = "password")
    public String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<Noteholder> noteholders = new HashSet<>();

    public boolean addNoteholder(Noteholder noteholder) {
        noteholder.setUser(this);
        return noteholders.add(noteholder);
    }
}
