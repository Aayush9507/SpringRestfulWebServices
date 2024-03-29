package com.springcourse.restfulwebservices.user;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user")

@Entity
public class User {

    @Id
    @GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;
	
	@ApiModelProperty(notes="birth date should be in past")
	private Date birth;

    @OneToMany(mappedBy="user")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

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
	
	@Past
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	public User(Integer id, String name, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
	}
	protected User() {}
	
}
