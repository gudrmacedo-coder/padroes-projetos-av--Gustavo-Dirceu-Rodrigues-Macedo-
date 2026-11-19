import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * SINGLETON: histórico de auditoria único em todo o programa (RNF05).
 * Os métodos são sincronizados, então registros simultâneos não se perdem
 * nem se sobrescrevem.
 */
public final class RegistroAuditoria {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private final List<String> registros = new ArrayList<>();

    private RegistroAuditoria() { }

    private static final class Holder {
        private static final RegistroAuditoria INSTANCIA = new RegistroAuditoria();
    }

    public static RegistroAuditoria getInstance() {
        return Holder.INSTANCIA;
    }

    public synchronized void registrar(String ambiente, String provedor, String resultado, String motivo) {
        String linha = LocalDateTime.now().format(FORMATO) + " | " + ambiente + " | " + provedor
                + " | " + resultado + " (" + motivo + ")";
        registros.add(linha);
    }

    public synchronized void imprimirHistorico() {
        System.out.println("\n=== HISTORICO DE PROVISIONAMENTOS ===");
        for (String r : registros) {
            System.out.println(r);
        }
    }
}
