/** OBSERVER (interface): quem acompanha um lote. Participante, painel e auditor implementam. */
public interface Interessado {
    String getNome();
    void atualizar(Lote lote, Lance lance);
}
