package ch.helvetia.jax2011.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 */
@Entity
public class Tag {
	
	@GeneratedValue
	@Id
	private Long id;
	
	private String name;

}
