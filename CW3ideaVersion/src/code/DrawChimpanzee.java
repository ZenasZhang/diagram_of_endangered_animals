/*
Jiyao Zhang
ID: 1822277
 */
package code;

public class DrawChimpanzee extends ReadAndCreate {

    public DrawChimpanzee() {
        super();
        super.setKey(2000);
        super.setAnimalName("Chimpanzee");
        super.read("src/resource/animal_data/Chimpanzee_Data.txt");
        super.getImage("src/resource/img/Chimpanzee.png","src/resource/img/halfChimpanzee.png");
        super.create();
    }

}
