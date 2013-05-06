package entity;

public enum BaseEntityStateEnum {

	ACTIVE("A", "Activo"), INACTIVE("I", "Inactivo"), DELETED("D", "Borrado");
	private final String name;
	private final String id;

	private BaseEntityStateEnum(String id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
}
