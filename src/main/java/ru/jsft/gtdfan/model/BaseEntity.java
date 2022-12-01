package ru.jsft.gtdfan.model;

import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.util.ProxyUtils;

import java.util.Objects;

@SuperBuilder
public abstract class BaseEntity {
    @Id
    protected Long id;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id + '}';
    }
}
