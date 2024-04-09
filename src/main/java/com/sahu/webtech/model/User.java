package com.sahu.webtech.model;

import java.util.Set;

import com.sahu.webtech.constant.Provider;
import com.sahu.webtech.constant.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_name", "email" }) })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuid;

	@Column(length = 50, name = "first_name")
	private String firstName;

	@Column(length = 50, name = "middle_name")
	private String middleName;

	@Column(length = 50, name = "last_name")
	private String lastName;

	private String email;

	@Column(name = "display_name")
	private String displayName;

	private String password;

	@Column(name = "photo_path")
	private String photoPath;

	@Enumerated(EnumType.STRING)
	private Provider provider;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Boolean active;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

}
