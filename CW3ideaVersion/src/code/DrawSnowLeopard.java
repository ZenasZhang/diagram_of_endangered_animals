/*
Jiyao Zhang
ID: 1822277
 */
package code;

public class DrawSnowLeopard extends ReadAndCreate{

    public DrawSnowLeopard() {
        super();
        super.setKey(400);
        super.setAnimalName("Snow Leopard");
        super.read("src/resource/animal_data/SnowLeopard_Data.txt");
        super.getImage("src/resource/img/SnowLeopard.png","src/resource/img/halfSnowLeopard.png");
        super.create();
    }

}
