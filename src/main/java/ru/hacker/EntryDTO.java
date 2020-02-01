package ru.hacker;

import java.util.Objects;

public class EntryDTO {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryDTO entryDTO = (EntryDTO) o;
        return id == entryDTO.id &&
                Objects.equals(name, entryDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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

    public EntryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "{id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
