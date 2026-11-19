import java.math.BigDecimal;

/** CONCRETE DECORATOR: certificado de autenticidade, R$ 300,00 fixos. */
public class CertificadoAutenticidade extends ServicoAdicional {
    public CertificadoAutenticidade(ItemFatura item) { super(item); }

    @Override protected String descricao() { return "Certificado de autenticidade"; }
    @Override protected BigDecimal custo(BigDecimal valorVenda) { return new BigDecimal("300.00"); }
}
