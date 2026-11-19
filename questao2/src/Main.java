import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

/** RF11: cenario completo da casa de leiloes. */
public class Main {
    public static void main(String[] args) {
        Categoria arte = new Categoria("Arte", new ComissaoArte());
        Categoria eletronicos = new Categoria("Eletronicos", new ComissaoEletronicos());

        Lote quadro = new Lote("Quadro Abstrato", arte, bd("1000"), bd("1300"));
        Lote notebook = new Lote("Notebook Gamer", eletronicos, bd("500"), bd("800"));

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        // Painel e auditor acompanham os dois lotes
        for (Lote lote : Arrays.asList(quadro, notebook)) {
            lote.acompanhar(painel);
            lote.acompanhar(auditor);
        }
        quadro.acompanhar(ana);
        quadro.acompanhar(bruno);
        notebook.acompanhar(carla);

        System.out.println("\n--- Lances no Quadro Abstrato ---");
        darLance(quadro, ana, "1050");
        darLance(quadro, bruno, "1100");   // recusado: menos de 5% acima de 1050
        darLance(quadro, bruno, "1200");

        System.out.println("\n--- Bruno deixa de acompanhar o quadro ---");
        quadro.deixarDeAcompanhar(bruno);
        darLance(quadro, ana, "1300");
        darLance(quadro, carla, "1400");

        System.out.println("\n--- Campanha: regra de Eletronicos aplicada ao quadro de Arte ---");
        quadro.trocarPolitica(eletronicos.getPolitica());

        System.out.println("\n--- Lances no Notebook Gamer ---");
        darLance(notebook, carla, "600");
        darLance(notebook, ana, "650");

        System.out.println("\n--- Encerramento ---");
        Optional<Venda> vendaNotebook = notebook.encerrar();
        if (vendaNotebook.isPresent()) {
            System.out.println("Notebook Gamer: VENDIDO");
        } else {
            System.out.println("Notebook Gamer: NAO VENDIDO (maior lance R$ 650,00 abaixo da reserva R$ 800,00)");
        }

        Optional<Venda> vendaQuadro = quadro.encerrar();
        darLance(quadro, ana, "2000");   // recusado: lote encerrado

        if (vendaQuadro.isPresent()) {
            Venda venda = vendaQuadro.get();
            System.out.println("Quadro Abstrato: VENDIDO para " + venda.getComprador());
            venda.imprimirAcerto();

            // Um comprador contrata 4 servicos, um deles duas vezes
            ItemFatura fatura = new EntregaExpressa(
                    new EmbalagemEspecial(
                    new EmbalagemEspecial(
                    new CertificadoAutenticidade(
                    new SeguroTransporte(venda)))));
            Fatura.imprimir(fatura);
        }

        auditor.imprimirRegistros();
    }

    private static void darLance(Lote lote, Interessado autor, String valor) {
        try {
            lote.darLance(autor, bd(valor));
            System.out.println(autor.getNome() + " deu lance de R$ " + valor + " em '" + lote.getNome() + "': ACEITO");
        } catch (LanceRecusadoException e) {
            System.out.println(autor.getNome() + ": RECUSADO - " + e.getMessage());
        }
    }

    private static BigDecimal bd(String valor) {
        return new BigDecimal(valor);
    }
}
