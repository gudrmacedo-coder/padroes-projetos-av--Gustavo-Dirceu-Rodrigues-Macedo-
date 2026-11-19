/** Dados da solicitacao de ambiente (RF03). */
public class SolicitacaoAmbiente {
    private final String nome;
    private final String provedor;
    private final int quantidadeMaquinas;
    private final int gb;

    public SolicitacaoAmbiente(String nome, String provedor, int quantidadeMaquinas, int gb) {
        this.nome = nome;
        this.provedor = provedor;
        this.quantidadeMaquinas = quantidadeMaquinas;
        this.gb = gb;
    }

    public String getNome() { return nome; }
    public String getProvedor() { return provedor; }
    public int getQuantidadeMaquinas() { return quantidadeMaquinas; }
    public int getGb() { return gb; }
}
