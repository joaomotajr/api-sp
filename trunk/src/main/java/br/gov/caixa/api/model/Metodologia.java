package br.gov.caixa.api.model;

public enum Metodologia {
	PUC(0), AGIL(1), ESTRUTURADA(2), PORTAL_WEB(3), DATAWAREHOUSE(4);

	private final int codigo;

	Metodologia(int codigo) {
		this.codigo = codigo;
	}

	int codigo() {
		return codigo;
	}

	public static Metodologia porCodigo(int codigo) {
		for (Metodologia metodologia : Metodologia.values()) {
			if (codigo == metodologia.codigo())
				return metodologia;
		}
		throw new IllegalArgumentException("tipo invalido");
	}

}
