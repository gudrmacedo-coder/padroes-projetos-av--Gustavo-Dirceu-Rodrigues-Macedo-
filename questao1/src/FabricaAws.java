import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/** CONCRETE FACTORY (AWS). Numeracao sequencial por tipo de recurso. */
public class FabricaAws implements FabricaRecursosNuvem {
    private final AtomicInteger seqMaquinas = new AtomicInteger(0);
    private final AtomicInteger seqArmazenamento = new AtomicInteger(0);
    private final AtomicInteger seqFilas = new AtomicInteger(0);

    @Override public String getProvedor() { return "AWS"; }

    @Override public MaquinaVirtual criarMaquinaVirtual() {
        return new AwsMaquinaVirtual("i-" + seqMaquinas.incrementAndGet());
    }

    @Override public Armazenamento criarArmazenamento(int gb) {
        return new AwsArmazenamento("s3://" + seqArmazenamento.incrementAndGet(), gb);
    }

    @Override public FilaMensagens criarFilaMensagens() {
        return new AwsFilaMensagens("sqs-" + seqFilas.incrementAndGet());
    }

    @Override public BigDecimal custoHoraMaquina() { return new BigDecimal("0.10"); }
    @Override public BigDecimal custoGbMes() { return new BigDecimal("0.023"); }
    @Override public BigDecimal custoFilaMes() { return new BigDecimal("0.40"); }
}
