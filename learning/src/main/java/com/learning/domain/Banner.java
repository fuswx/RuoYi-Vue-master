package com.learning.domain;

import java.util.Objects;

public class Banner {
    private Integer id;
    private String img;
    private Integer sort;
    private Integer createtime;
    private Integer updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return Objects.equals(id, banner.id) && Objects.equals(img, banner.img) && Objects.equals(sort, banner.sort) && Objects.equals(createtime, banner.createtime) && Objects.equals(updatetime, banner.updatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, img, sort, createtime, updatetime);
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", sort=" + sort +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}

