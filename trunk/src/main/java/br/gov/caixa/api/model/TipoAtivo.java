package br.gov.caixa.api.model;

public enum TipoAtivo {
	DISCIPLINA(0), 
	FERRAMENTAS(1);
	
	private final int codigo;
	
	TipoAtivo(int codigo) { this.codigo = codigo; }
	
	int codigo() { return codigo; }
	
	public static TipoAtivo porCodigo(int codigo) {
        for (TipoAtivo tipoAtivo: TipoAtivo.values()) {
            if (codigo == tipoAtivo.codigo()) return tipoAtivo;
        }
        throw new IllegalArgumentException("tipo invalido");
    }
}
