package com.spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cheatsheet extends Base  {
	private String name, description, color, content, style, type, language, layout;
	private User user;
	private Section section;
	private Subsection subsection;
}
