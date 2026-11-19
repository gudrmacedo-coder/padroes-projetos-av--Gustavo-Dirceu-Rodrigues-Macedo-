import java.math.BigDecimal;
import java.math.RoundingMode;

/** CONCRETE STRATEGY: Veiculos, 8% do valor limitado a R$ 5.000,00. */
public class ComissaoVeiculos implements PoliticaComissao {
    private static final BigDecimal TAXA = new BigDecimal("0.08");
    private static final BigDecimal LIMITE = new BigDecimal("5000.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = valorVenda.multiply(TAXA).setScale(2, RoundingMode.HALF_UP);
        return comissao.min(LIMITE);
    }
}
