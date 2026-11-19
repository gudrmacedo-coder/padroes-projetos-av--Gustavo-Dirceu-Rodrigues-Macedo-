import java.math.BigDecimal;
import java.math.RoundingMode;

/** CONCRETE STRATEGY: Imoveis, 3% do valor mais R$ 1.500,00 fixos. */
public class ComissaoImoveis implements PoliticaComissao {
    private static final BigDecimal TAXA = new BigDecimal("0.03");
    private static final BigDecimal FIXO = new BigDecimal("1500.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(TAXA).add(FIXO).setScale(2, RoundingMode.HALF_UP);
    }
}
