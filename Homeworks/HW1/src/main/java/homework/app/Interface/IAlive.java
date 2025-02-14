package homework.app.Interface;


/**
    Интерфейс который реализовывают классы живых существ (сейчас: животные, в будущем: персонал)
 **/
public interface IAlive {
    /**
     * Уровень здоровья живого существа
     * @return уровень здоровья
     */
    public int getHealth();
    public void setHealth(int newHealth);
    public int getFood();

}
