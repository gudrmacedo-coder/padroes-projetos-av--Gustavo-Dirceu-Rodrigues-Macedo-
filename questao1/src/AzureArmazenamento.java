/** CONCRETE PRODUCT (Azure). */
public class AzureArmazenamento extends RecursoBase implements Armazenamento {
    private final int gb;
    public AzureArmazenamento(String id, int gb) { super(id, "Azure"); this.gb = gb; }
    @Override public int getGb() { return gb; }
}
