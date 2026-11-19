import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * SINGLETON: uma única instância de configuração, lida do config.properties
 * uma única vez durante toda a execução (RNF04).
 */
public final class ConfiguracaoAmbiente {
    private static final String ARQUIVO = "config.properties";

    private final String regiaoPadrao;
    private final BigDecimal orcamentoMensal;

    private ConfiguracaoAmbiente() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(ARQUIVO)) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("Aviso: " + ARQUIVO + " nao encontrado; usando valores padrao.");
        }
        this.regiaoPadrao = props.getProperty("regiao.padrao", "sa-east-1");
        this.orcamentoMensal = new BigDecimal(props.getProperty("orcamento.mensal.maximo", "500.00"));
        System.out.println("Configuracao carregada: regiao=" + regiaoPadrao + ", orcamento=R$ " + orcamentoMensal);
    }

    private static final class Holder {
        private static final ConfiguracaoAmbiente INSTANCIA = new ConfiguracaoAmbiente();
    }

    public static ConfiguracaoAmbiente getInstance() {
        return Holder.INSTANCIA;
    }

    public String getRegiaoPadrao() { return regiaoPadrao; }
    public BigDecimal getOrcamentoMensal() { return orcamentoMensal; }
}
