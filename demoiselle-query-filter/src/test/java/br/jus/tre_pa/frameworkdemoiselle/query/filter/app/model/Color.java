package br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Color {

    public Color(){

    }

    public Color(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
