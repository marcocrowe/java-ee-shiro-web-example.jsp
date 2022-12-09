package io.gitlab.markcrowe;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

/**
 * User
 */
@Entity
@Getter
@Setter
public class User implements Serializable
{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "Id")
	private int id;


	@Basic
	@Column(name = "Email")
	private String email;

	@Basic
	@Column(name = "HashedPassword")
	private String hashedPassword;

	@Basic
	@Column(name = "Name")
	private String name;

	@Basic
	@Column(name = "PlainTextPassword")
	private String plainTextPassword;

	@Basic
	@Column(name = "Salt")
	private String salt;

	@Basic
	@Column(name = "Username")
	private String username;
}
