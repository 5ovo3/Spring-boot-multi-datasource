package kr.engsoft.test3.shelter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
public class Shelter {
    @Id
    @Column(name = "provider_id")
    private String provider_id;

    @Column(name = "map_id")
    private String map_id;

}
