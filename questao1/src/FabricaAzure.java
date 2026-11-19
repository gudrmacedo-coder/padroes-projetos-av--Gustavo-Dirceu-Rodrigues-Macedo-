import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/** CONCRETE FACTORY (Azure). Numeracao sequencial por tipo de recurso. */
public class FabricaAzure implements FabricaRecursosNuvem {
    private final AtomicInteger seqMaquinas = new AtomicInteger(0);
    private final AtomicInteger seqArmazenamento = new AtomicInteger(0);
    private final AtomicInteger seqFilas = new AtomicInteger(0);

    @Override public String getProvedor() { return "Azure"; }

    @Override public MaquinaVirtual criarMaquinaVirtual() {
        return new AzureMaquinaVirtual("vm-" + seqMaquinas.incrementAndGet());
    }

    @Override public Armazenamento criarArmazenamento(int gb) {
        return new AzureArmazenamento("blob://" + seqArmazenamento.incrementAndGet(), gb);
    }

    @Override public FilaMensagens criarFilaMensagens() {
        return new AzureFilaMensagens("sb-" + seqFilas.incrementAndGet());
    }

    @Override public BigDecimal custoHoraMaquina() { return new BigDecimal("0.12"); }
    @Override public BigDecimal custoGbMes() { return new BigDecimal("0.02"); }
    @Override public BigDecimal custoFilaMes() { return new BigDecimal("0.50"); }
}
