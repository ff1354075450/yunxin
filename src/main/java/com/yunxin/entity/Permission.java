package com.yunxin.entity;

public class Permission {

    private Long id;

    private String permission;//权限标识 程序中判断使用

    private String description;//权限描述，UI界面显示使用

    private Boolean availbale = Boolean.FALSE;//是否可用，如果不可用将不会添加给用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailbale() {
        return availbale;
    }

    public void setAvailbale(Boolean availbale) {
        this.availbale = availbale;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission permission = (Permission) o;

        if (id != null ? !id.equals(permission.id) : permission.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", availbale=" + availbale +
                '}';
    }
}
