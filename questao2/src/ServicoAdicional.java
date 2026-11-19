import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DECORATOR (Decorator abstrato): envolve um ItemFatura e acrescenta um servico.
 * Servicos podem ser combinados em qualquer ordem e quantidade (RNF03).
 */
public abstract class ServicoAdicional implements ItemFatura {
    protected final ItemFatura item;

    protected ServicoAdicional(ItemFatura item) {
        this.item = item;
    }

    protected abstract String descricao();
    protected abstract BigDecimal custo(BigDecimal valorVenda);

    @Override
    public BigDecimal valorVenda() {
        return item.valorVenda();
    }

    @Override
    public List<LinhaFatura> linhas() {
        List<LinhaFatura> linhas = new ArrayList<>(item.linhas());
        linhas.add(new LinhaFatura(descricao(), custo(valorVenda())));
        return linhas;
    }

    @Override
    public BigDecimal total() {
        return item.total().add(custo(valorVenda()));
    }
}
