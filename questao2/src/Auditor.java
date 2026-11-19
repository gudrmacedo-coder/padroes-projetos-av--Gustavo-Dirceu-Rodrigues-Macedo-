import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** OBSERVER (ConcreteObserver): registra todos os lances aceitos com data e hora. */
public class Auditor implements Interessado {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final List<String> registros = new ArrayList<>();

    @Override public String getNome() { return "Auditor"; }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        registros.add(String.format("%s | Lote '%s' | %s | R$ %.2f",
                lance.getDataHora().format(FORMATO), lote.getNome(),
                lance.getAutor().getNome(), lance.getValor()));
    }

    public void imprimirRegistros() {
        System.out.println("\n=== REGISTRO DE LANCES (AUDITOR) ===");
        registros.forEach(System.out::println);
    }
}
