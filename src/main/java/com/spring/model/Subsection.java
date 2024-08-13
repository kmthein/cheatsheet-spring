package com.spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subsection extends Base {
	private String name;
	private String type;
	private Section section;
}
