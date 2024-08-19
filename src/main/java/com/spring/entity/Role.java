package com.spring.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Role {
	@Enumerated(EnumType.STRING)
	NONE,
	@Enumerated(EnumType.STRING)
	USER,
	@Enumerated(EnumType.STRING)
	ADMIN;
}
