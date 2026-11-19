import java.math.BigDecimal;

/** CONCRETE DECORATOR: embalagem especial, R$ 180,00 fixos. */
public class EmbalagemEspecial extends ServicoAdicional {
    public EmbalagemEspecial(ItemFatura item) { super(item); }

    @Override protected String descricao() { return "Embalagem especial"; }
    @Override protected BigDecimal custo(BigDecimal valorVenda) { return new BigDecimal("180.00"); }
}
