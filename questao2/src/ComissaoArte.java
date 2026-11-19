import java.math.BigDecimal;
import java.math.RoundingMode;

/** CONCRETE STRATEGY: Arte, 20% do valor. */
public class ComissaoArte implements PoliticaComissao {
    private static final BigDecimal TAXA = new BigDecimal("0.20");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(TAXA).setScale(2, RoundingMode.HALF_UP);
    }
}
