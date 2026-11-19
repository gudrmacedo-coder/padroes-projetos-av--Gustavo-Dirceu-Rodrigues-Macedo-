import java.math.BigDecimal;

/**
 * Imprime a fatura (RF09). Trata venda sem servicos e venda com qualquer quantidade
 * de servicos da mesma forma, sem verificar quais servicos foram contratados (RNF04).
 */
public final class Fatura {
    private Fatura() { }

    public static void imprimir(ItemFatura item) {
        System.out.println("\n=== FATURA DO COMPRADOR ===");
        for (ItemFatura.LinhaFatura linha : item.linhas()) {
            System.out.printf("%-45s R$ %10.2f%n", linha.getDescricao(), linha.getValor());
        }
        System.out.printf("%-45s R$ %10.2f%n", "TOTAL A PAGAR", item.total());
    }
}
