import java.math.BigDecimal;
import java.math.RoundingMode;

/** CONCRETE DECORATOR: seguro de transporte, 1,5% do valor de venda. */
public class SeguroTransporte extends ServicoAdicional {
    public SeguroTransporte(ItemFatura item) { super(item); }

    @Override protected String descricao() { return "Seguro de transporte (1,5%)"; }

    @Override
    protected BigDecimal custo(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
    }
}
