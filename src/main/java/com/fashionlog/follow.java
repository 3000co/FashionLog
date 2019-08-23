package com.fashionlog;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(schema = "TEST", name = "FOLLOW")
public class follow implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Set<follow> followers;
    private Set<follow> following;

    public follow() {

    }

    public follow(String name) {
        this.name = name;
        this.followers = new HashSet<follow>();
        this.following = new HashSet<follow>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "TEST", name = "USER_RELATIONS",
        joinColumns = @JoinColumn(name = "FOLLOWED_ID"),
        inverseJoinColumns = @JoinColumn(name = "FOLLOWER_ID"))
    public Set<follow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<follow> followers) {
        this.followers = followers;
    }

    public void addFollower(follow follower) {
        followers.add(follower);
        follower.following.add(this);
    }

    @ManyToMany(mappedBy = "followers")
    public Set<follow> getFollowing() {
        return following;
    }  

    public void setFollowing(Set<follow> following) {
        this.following = following;
    }

    public void addFollowing(follow followed) {
        followed.addFollower(this);
    }

}