import java.math.BigDecimal;
import java.math.RoundingMode;

/** CONCRETE STRATEGY: Eletronicos, 12% do valor com minimo de R$ 50,00. */
public class ComissaoEletronicos implements PoliticaComissao {
    private static final BigDecimal TAXA = new BigDecimal("0.12");
    private static final BigDecimal MINIMO = new BigDecimal("50.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = valorVenda.multiply(TAXA).setScale(2, RoundingMode.HALF_UP);
        return comissao.max(MINIMO);
    }
}
