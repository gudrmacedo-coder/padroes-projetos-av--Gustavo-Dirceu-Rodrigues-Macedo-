import java.math.BigDecimal;

/** CONCRETE DECORATOR: entrega expressa, R$ 450,00 fixos. */
public class EntregaExpressa extends ServicoAdicional {
    public EntregaExpressa(ItemFatura item) { super(item); }

    @Override protected String descricao() { return "Entrega expressa"; }
    @Override protected BigDecimal custo(BigDecimal valorVenda) { return new BigDecimal("450.00"); }
}
