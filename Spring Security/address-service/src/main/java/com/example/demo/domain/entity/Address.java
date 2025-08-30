package com.example.demo.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address", 
	uniqueConstraints = {
	    @UniqueConstraint(
	        name = "uk_user_main_address",
	        columnNames = {"user_id", "main"}
	    )
	}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
	private String cp;
	
	private String street;
	
	private String houseNumber;
	
	@Column(nullable = false)
	private Boolean main;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;

}