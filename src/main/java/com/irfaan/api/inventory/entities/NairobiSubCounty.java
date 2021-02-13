package com.irfaan.api.inventory.entities;

import org.locationtech.jts.geom.MultiPolygon;

import javax.persistence.*;

@Table(name = "nairobi_sub_counties")
@Entity
public class NairobiSubCounty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "geom")
    private MultiPolygon geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiPolygon getGeom() {
        return geom;
    }

    public void setGeom(MultiPolygon geom) {
        this.geom = geom;
    }
}
