import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DECORATOR (ConcreteComponent): a venda base. Os servicos adicionais envolvem esta classe.
 * Tambem gera o acerto com o vendedor (RF10).
 */
public class Venda implements ItemFatura {
    private final String nomeLote;
    private final String comprador;
    private final BigDecimal valorVenda;
    private final BigDecimal comissao;

    public Venda(String nomeLote, String comprador, BigDecimal valorVenda, BigDecimal comissao) {
        this.nomeLote = nomeLote;
        this.comprador = comprador;
        this.valorVenda = valorVenda;
        this.comissao = comissao;
    }

    @Override public BigDecimal valorVenda() { return valorVenda; }

    @Override
    public List<LinhaFatura> linhas() {
        List<LinhaFatura> linhas = new ArrayList<>();
        linhas.add(new LinhaFatura("Lote '" + nomeLote + "' (valor de venda)", valorVenda));
        return linhas;
    }

    @Override public BigDecimal total() { return valorVenda; }

    public String getComprador() { return comprador; }

    public void imprimirAcerto() {
        System.out.printf("%n=== ACERTO DO VENDEDOR - Lote '%s' ===%n", nomeLote);
        System.out.printf("Valor de venda:            R$ %.2f%n", valorVenda);
        System.out.printf("Comissao cobrada:          R$ %.2f%n", comissao);
        System.out.printf("Valor liquido a repassar:  R$ %.2f%n", valorVenda.subtract(comissao));
    }
}
