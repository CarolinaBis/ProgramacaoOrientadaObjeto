package domain.entity;

public enum TipoSanguineo {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private final String valor;

    TipoSanguineo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoSanguineo fromString(String texto) {
        for (TipoSanguineo tipo : TipoSanguineo.values()) {
            if (tipo.valor.equals(texto)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo sanguíneo inválido: " + texto);
    }
}