/**
 * Categoria do lote. Carrega a regra de comissao padrao. Uma nova categoria
 * e uma nova instancia com sua propria politica, sem alterar Lote.
 */
public class Categoria {
    private final String nome;
    private final PoliticaComissao politica;

    public Categoria(String nome, PoliticaComissao politica) {
        this.nome = nome;
        this.politica = politica;
    }

    public String getNome() { return nome; }
    public PoliticaComissao getPolitica() { return politica; }
}
