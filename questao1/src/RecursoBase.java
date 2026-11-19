public abstract class RecursoBase implements RecursoNuvem {
    private final String id;
    private final String provedor;

    protected RecursoBase(String id, String provedor) {
        this.id = id;
        this.provedor = provedor;
    }

    @Override public String getId() { return id; }
    @Override public String getProvedor() { return provedor; }
}
