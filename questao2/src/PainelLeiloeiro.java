import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/** OBSERVER (ConcreteObserver): painel do leiloeiro com maior lance e total de lances por lote. */
public class PainelLeiloeiro implements Interessado {
    private final Map<String, BigDecimal> maiorLance = new HashMap<>();
    private final Map<String, Integer> totalLances = new HashMap<>();

    @Override public String getNome() { return "Painel do Leiloeiro"; }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        String chave = lote.getNome();
        maiorLance.merge(chave, lance.getValor(), BigDecimal::max);
        totalLances.merge(chave, 1, Integer::sum);
        System.out.printf("  [Painel] Lote '%s': maior lance R$ %.2f | total de lances: %d%n",
                chave, maiorLance.get(chave), totalLances.get(chave));
    }
}
