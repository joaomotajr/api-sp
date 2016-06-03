package br.gov.caixa.api.model;

public enum TipoTimeline {
	NENHUM(0),
	UM(1), 
	DOIS(2),
	TRES(3);
	
	private final int codigo;
	
	TipoTimeline(int codigo) { this.codigo = codigo; }
	
	int codigo() { return codigo; }
	
	public static TipoTimeline porCodigo(int codigo) {
        for (TipoTimeline tipoTimeline: TipoTimeline.values()) {
            if (codigo == tipoTimeline.codigo()) return tipoTimeline;
        }
        throw new IllegalArgumentException("tipo invalido");
    }
}
