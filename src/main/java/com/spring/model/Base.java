package com.spring.model;

import java.time.LocalDateTime;

public class Base {
	private int id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String updatedAtFormatted;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUpdatedAtFormatted() {
		return updatedAtFormatted;
	}
	public void setUpdatedAtFormatted(String updatedAtFormatted) {
		this.updatedAtFormatted = updatedAtFormatted;
	}
	
}