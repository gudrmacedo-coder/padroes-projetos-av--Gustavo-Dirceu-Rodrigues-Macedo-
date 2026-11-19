import java.math.BigDecimal;

/** STRATEGY (interface): regra de calculo da comissao do vendedor (RNF02). */
public interface PoliticaComissao {
    BigDecimal calcular(BigDecimal valorVenda);
}
