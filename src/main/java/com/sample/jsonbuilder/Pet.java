package com.sample.jsonbuilder;

import lombok.Getter;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pet {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls = new ArrayList<>();
	private List<Tag> tags = new ArrayList<>();
	private Status status;

	public static class Builder {
		private Integer id;
		private Category category;
		private String name;
		private List<String> photoUrls = new ArrayList<>();
		private List<Tag> tags = new ArrayList<>();
		private Status status;

		public Builder() {

		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder inCategory(Category category) {
			this.category = category;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withPhotoUrls(List<String> photoUrls) {
			this.photoUrls = photoUrls;
			return this;
		}

		public Builder withTags(List<Tag> tags) {
			this.tags = tags;
			return this;
		}

		public Builder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public Pet build() {
			Pet pet = new Pet();
			pet.id = this.id;
			pet.name = this.name;
			pet.category = this.category;
			pet.photoUrls = this.photoUrls;
			pet.tags = this.tags;
			pet.status = this.status;
			return pet;
		}

	}

}
