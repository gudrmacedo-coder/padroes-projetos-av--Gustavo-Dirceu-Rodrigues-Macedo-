import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * OBSERVER (ConcreteSubject): notifica os interessados que acompanham o lote, exceto o autor do lance.
 * STRATEGY (Context): usa uma PoliticaComissao trocavel em tempo de execucao (RF07).
 * Nao conhece classes concretas de interessados nem de comissao (RNF01, RNF02).
 */
public class Lote implements Sujeito {
    private static final BigDecimal INCREMENTO_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoReserva;
    private BigDecimal lanceAtual;
    private final List<Lance> lances = new ArrayList<>();
    private final List<Interessado> acompanhantes = new ArrayList<>();
    private PoliticaComissao politicaComissao;
    private boolean encerrado = false;

    public Lote(String nome, Categoria categoria, BigDecimal precoInicial, BigDecimal precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial;
        this.politicaComissao = categoria.getPolitica();
    }

    public void darLance(Interessado autor, BigDecimal valor) throws LanceRecusadoException {
        if (encerrado) {
            throw new LanceRecusadoException("Lote '" + nome + "' esta encerrado e nao aceita lances.");
        }
        BigDecimal minimo = lanceAtual.multiply(INCREMENTO_MINIMO);
        if (valor.compareTo(minimo) < 0) {
            throw new LanceRecusadoException(String.format(
                    "lance de R$ %.2f recusado: o minimo e R$ %.2f (5%% acima do lance atual de R$ %.2f).",
                    valor, minimo, lanceAtual));
        }
        lanceAtual = valor;
        Lance lance = new Lance(autor, valor);
        lances.add(lance);
        notificarInteressados(lance);
    }

    @Override
    public void acompanhar(Interessado interessado) {
        if (!acompanhantes.contains(interessado)) {
            acompanhantes.add(interessado);
        }
    }

    @Override
    public void deixarDeAcompanhar(Interessado interessado) {
        acompanhantes.remove(interessado);
    }

    @Override
    public void notificarInteressados(Lance lance) {
        for (Interessado i : new ArrayList<>(acompanhantes)) {
            if (i != lance.getAutor()) {
                i.atualizar(this, lance);
            }
        }
    }

    public void trocarPolitica(PoliticaComissao novaPolitica) {
        if (encerrado) {
            throw new IllegalStateException("Lote encerrado: a regra nao pode mais ser trocada.");
        }
        this.politicaComissao = novaPolitica;
    }

    /** RF05 e RF07: vende ao maior lance se atingir a reserva; a comissao usa a regra vigente agora. */
    public Optional<Venda> encerrar() {
        if (encerrado) {
            throw new IllegalStateException("Lote '" + nome + "' ja foi encerrado.");
        }
        encerrado = true;
        if (lances.isEmpty() || lanceAtual.compareTo(precoReserva) < 0) {
            return Optional.empty();
        }
        Lance vencedor = lances.get(lances.size() - 1);
        BigDecimal comissao = politicaComissao.calcular(lanceAtual);
        return Optional.of(new Venda(nome, vencedor.getAutor().getNome(), lanceAtual, comissao));
    }

    public String getNome() { return nome; }
    public Categoria getCategoria() { return categoria; }
    public BigDecimal getLanceAtual() { return lanceAtual; }
    public BigDecimal getPrecoReserva() { return precoReserva; }
    public boolean isEncerrado() { return encerrado; }
}
