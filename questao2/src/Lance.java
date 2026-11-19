import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Lance aceito: autor (interface Interessado), valor e data/hora. */
public class Lance {
    private final Interessado autor;
    private final BigDecimal valor;
    private final LocalDateTime dataHora;

    public Lance(Interessado autor, BigDecimal valor) {
        this.autor = autor;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public Interessado getAutor() { return autor; }
    public BigDecimal getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
}
