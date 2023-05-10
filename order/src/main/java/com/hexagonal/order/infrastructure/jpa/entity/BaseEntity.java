package com.hexagonal.order.infrastructure.jpa.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	@PrePersist
	public void init(){
		createdAt = LocalDate.now();
	}

	@CreatedDate
	private LocalDate createdAt;
	@LastModifiedDate
	private LocalDate modifiedAt;
}
