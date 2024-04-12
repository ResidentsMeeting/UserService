package com.example.user_service.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRoleToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String token;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean expired;
	@Embedded
	private Address address;

	public static UserRoleToken getInstance(String token, UserRole role, Address address) {
		return new UserRoleToken(null, token, role, false, address);
	}

	public void expire() {
		this.expired = true;
	}
}
