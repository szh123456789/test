//package com.test;
//
//import javax.persistence.*;
//
//
//@Entity
//@Table(name = "stu", schema = "edu", catalog = "")
//public class StuEntity {
//    private String id;
//    private String name;
//    private String tel;
//
//    @Id
//    @Column(name = "id")
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "name")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Basic
//    @Column(name = "tel")
//    public String getTel() {
//        return tel;
//    }
//
//    public void setTel(String tel) {
//        this.tel = tel;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        StuEntity stuEntity = (StuEntity) o;
//
//        if (id != null ? !id.equals(stuEntity.id) : stuEntity.id != null) return false;
//        if (name != null ? !name.equals(stuEntity.name) : stuEntity.name != null) return false;
//        if (tel != null ? !tel.equals(stuEntity.tel) : stuEntity.tel != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (tel != null ? tel.hashCode() : 0);
//        return result;
//    }
//}
