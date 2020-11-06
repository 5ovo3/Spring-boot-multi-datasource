package kr.engsoft.test3.shelter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ShelterMember {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

}
