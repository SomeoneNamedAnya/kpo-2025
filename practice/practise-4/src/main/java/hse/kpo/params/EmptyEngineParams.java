package hse.kpo.params;
/**
 * Запись - параметр для двигателей без параметров
 */
public record EmptyEngineParams() {
    public static final EmptyEngineParams DEFAULT = new EmptyEngineParams();
}
