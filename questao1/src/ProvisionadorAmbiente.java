import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * CLIENT do Abstract Factory. Recebe a fabrica pronta de fora (RNF02) e nao conhece
 * classes concretas de provedor nem decide o provedor com if/switch.
 */
public class ProvisionadorAmbiente {
    private static final BigDecimal HORAS_MES = new BigDecimal("730");

    private final FabricaRecursosNuvem fabrica;

    public ProvisionadorAmbiente(FabricaRecursosNuvem fabrica) {
        this.fabrica = fabrica;
    }

    public void provisionar(SolicitacaoAmbiente s) {
        ConfiguracaoAmbiente config = ConfiguracaoAmbiente.getInstance();
        RegistroAuditoria auditoria = RegistroAuditoria.getInstance();
        String provedor = fabrica.getProvedor();

        if (!fabrica.atende(s.getProvedor())) {
            recusar(s, "provedor solicitado (" + s.getProvedor() + ") diferente do provisionador (" + provedor + ")");
            return;
        }

        BigDecimal custo = calcularCusto(s);
        if (custo.compareTo(config.getOrcamentoMensal()) > 0) {
            recusar(s, "custo estimado R$ " + custo + " ultrapassa o orcamento de R$ " + config.getOrcamentoMensal());
            return;
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < s.getQuantidadeMaquinas(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }
        Armazenamento armazenamento = fabrica.criarArmazenamento(s.getGb());
        FilaMensagens fila = fabrica.criarFilaMensagens();

        AmbienteNuvem ambiente = new AmbienteNuvem(s.getNome(), provedor, config.getRegiaoPadrao(),
                maquinas, armazenamento, fila, custo);
        System.out.println(ambiente.resumo());
        auditoria.registrar(s.getNome(), provedor, "ACEITO", "custo dentro do orcamento");
    }

    /** RF04: maquinas x custo/hora x 730 + GB x custo/GB + custo fixo da fila. */
    private BigDecimal calcularCusto(SolicitacaoAmbiente s) {
        BigDecimal maquinas = fabrica.custoHoraMaquina()
                .multiply(HORAS_MES)
                .multiply(BigDecimal.valueOf(s.getQuantidadeMaquinas()));
        BigDecimal armazenamento = fabrica.custoGbMes().multiply(BigDecimal.valueOf(s.getGb()));
        return maquinas.add(armazenamento).add(fabrica.custoFilaMes()).setScale(2, RoundingMode.HALF_UP);
    }

    private void recusar(SolicitacaoAmbiente s, String motivo) {
        System.out.println("RECUSADO: " + s.getNome() + " - " + motivo);
        RegistroAuditoria.getInstance().registrar(s.getNome(), fabrica.getProvedor(), "RECUSADO", motivo);
    }
}
