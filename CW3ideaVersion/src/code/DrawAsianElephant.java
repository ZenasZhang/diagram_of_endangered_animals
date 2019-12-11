/*
Jiyao Zhang
ID: 1822277
 */
package code;

public class DrawAsianElephant extends ReadAndCreate {

    public DrawAsianElephant() {
        super();
        super.setKey(3000);
        super.setAnimalName("Asian Elephant");
        super.read("src/resource/animal_data/AsianElephant_Data.txt");
        super.getImage("src/resource/img/AsianElephant.png","src/resource/img/halfAsianElephant.png");
        super.create();
    }

}
