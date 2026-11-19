import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Ambiente montado. O construtor impede recursos de provedores diferentes (RNF01).
 */
public class AmbienteNuvem {
    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinas;
    private final Armazenamento armazenamento;
    private final FilaMensagens fila;
    private final BigDecimal custoMensal;

    public AmbienteNuvem(String nome, String provedor, String regiao, List<MaquinaVirtual> maquinas,
                         Armazenamento armazenamento, FilaMensagens fila, BigDecimal custoMensal) {
        List<RecursoNuvem> todos = new ArrayList<>(maquinas);
        todos.add(armazenamento);
        todos.add(fila);
        for (RecursoNuvem r : todos) {
            if (!r.getProvedor().equals(provedor)) {
                throw new IllegalArgumentException("Recurso " + r.getId() + " pertence a " + r.getProvedor()
                        + ", nao a " + provedor);
            }
        }
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = new ArrayList<>(maquinas);
        this.armazenamento = armazenamento;
        this.fila = fila;
        this.custoMensal = custoMensal;
    }

    public String resumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== AMBIENTE PROVISIONADO ===\n");
        sb.append("Nome: ").append(nome).append('\n');
        sb.append("Provedor: ").append(provedor).append('\n');
        sb.append("Regiao: ").append(regiao).append('\n');
        for (MaquinaVirtual m : maquinas) {
            sb.append("  Maquina virtual: ").append(m.getId()).append('\n');
        }
        sb.append("  Armazenamento: ").append(armazenamento.getId())
          .append(" (").append(armazenamento.getGb()).append(" GB)\n");
        sb.append("  Fila: ").append(fila.getId()).append('\n');
        sb.append("Custo mensal estimado: R$ ").append(custoMensal);
        return sb.toString();
    }
}
