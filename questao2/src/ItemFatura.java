import java.math.BigDecimal;
import java.util.List;

/** DECORATOR (Component): item que pode ser faturado e decorado com servicos adicionais. */
public interface ItemFatura {
    BigDecimal valorVenda();
    List<LinhaFatura> linhas();
    BigDecimal total();

    /** Uma linha da fatura: descricao e valor. */
    final class LinhaFatura {
        private final String descricao;
        private final BigDecimal valor;

        public LinhaFatura(String descricao, BigDecimal valor) {
            this.descricao = descricao;
            this.valor = valor;
        }

        public String getDescricao() { return descricao; }
        public BigDecimal getValor() { return valor; }
    }
}
