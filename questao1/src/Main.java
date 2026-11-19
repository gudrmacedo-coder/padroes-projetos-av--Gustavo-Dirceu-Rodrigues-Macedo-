/** RF08: dois provisionamentos na AWS (simultaneos), um no Azure e um recusado por orcamento. */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProvisionadorAmbiente aws = new ProvisionadorAmbiente(new FabricaAws());
        ProvisionadorAmbiente azure = new ProvisionadorAmbiente(new FabricaAzure());

        SolicitacaoAmbiente s1 = new SolicitacaoAmbiente("ambiente-web", "AWS", 2, 100);
        SolicitacaoAmbiente s2 = new SolicitacaoAmbiente("ambiente-dados", "AWS", 1, 50);
        SolicitacaoAmbiente s3 = new SolicitacaoAmbiente("ambiente-app", "Azure", 3, 200);
        SolicitacaoAmbiente s4 = new SolicitacaoAmbiente("ambiente-grande", "Azure", 10, 500);

        Thread t1 = new Thread(() -> aws.provisionar(s1), "provisionamento-1");
        Thread t2 = new Thread(() -> aws.provisionar(s2), "provisionamento-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        azure.provisionar(s3);
        azure.provisionar(s4);

        RegistroAuditoria.getInstance().imprimirHistorico();
    }
}
