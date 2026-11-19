import java.math.BigDecimal;

/** OBSERVER (ConcreteObserver): participante que pode dar lances e acompanhar lotes. */
public class Participante implements Interessado {
    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override public String getNome() { return nome; }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.printf("  [Participante %s] Lote '%s': novo lance de R$ %.2f%n",
                nome, lote.getNome(), lance.getValor());
    }
}
