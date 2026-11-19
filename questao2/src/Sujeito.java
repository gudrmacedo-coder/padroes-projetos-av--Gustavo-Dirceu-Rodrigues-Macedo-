/** OBSERVER (Subject): contrato de quem notifica os interessados. */
public interface Sujeito {
    void acompanhar(Interessado interessado);
    void deixarDeAcompanhar(Interessado interessado);
    void notificarInteressados(Lance lance);
}
