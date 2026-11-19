/** CONCRETE PRODUCT (AWS). */
public class AwsArmazenamento extends RecursoBase implements Armazenamento {
    private final int gb;
    public AwsArmazenamento(String id, int gb) { super(id, "AWS"); this.gb = gb; }
    @Override public int getGb() { return gb; }
}
