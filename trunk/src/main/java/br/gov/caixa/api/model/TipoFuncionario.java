package br.gov.caixa.api.model;

public enum TipoFuncionario {
	OUTROS(0), COORDENADOR_PROJETO(1),	COORDENADOR_TI (2), GERENTE_TI (3);
	
	private final int codigo;
	
	TipoFuncionario(int codigo) { this.codigo = codigo; }

    int codigo() { return codigo; }

    public static TipoFuncionario porCodigo(int codigo) {
        for (TipoFuncionario tipoFuncionario: TipoFuncionario.values()) {
            if (codigo == tipoFuncionario.codigo()) return tipoFuncionario;
        }
        throw new IllegalArgumentException("tipo invalido");
    }    
}
