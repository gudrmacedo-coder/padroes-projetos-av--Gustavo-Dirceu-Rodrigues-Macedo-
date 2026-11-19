import java.math.BigDecimal;

/**
 * ABSTRACT FACTORY: cria os tres recursos de um mesmo provedor (RNF01, RNF02).
 * Tambem informa os precos do provedor, para o calculo do custo antes de criar recursos.
 */
public interface FabricaRecursosNuvem {
    String getProvedor();

    MaquinaVirtual criarMaquinaVirtual();
    Armazenamento criarArmazenamento(int gb);
    FilaMensagens criarFilaMensagens();

    BigDecimal custoHoraMaquina();
    BigDecimal custoGbMes();
    BigDecimal custoFilaMes();

    default boolean atende(String provedor) {
        return getProvedor().equalsIgnoreCase(provedor);
    }
}
