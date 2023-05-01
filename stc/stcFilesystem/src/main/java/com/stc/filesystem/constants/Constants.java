package com.stc.filesystem.constants;

public class Constants {

	public enum PermissionLevel {
		EDIT("EDIT"), DAWNLOAD("DAWNLOAD"), VIEW("VIEW");

		private PermissionLevel(String value) {
			this.value = value;
		}

		public final String value;

		public String getValue() {
			return value;
		}

	}

	public enum ItemType {
		SPACE("SPACE"), FOLDER("FOLDER"), FILE("FILE");

		public final String value;

		private ItemType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	
}
