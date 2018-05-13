package exercise2;

import exercise1.Peterson;

public class Lake {
    private volatile int waterLevel;
    private Peterson peteDam;
    private Peterson peteRiver;
    private Peterson peteDamnRivers; // Yeah, damn rivers tho.

    public Lake(int waterLevel){
        this.waterLevel = waterLevel;
        peteDam = new Peterson();
        peteRiver = new Peterson();
        peteDamnRivers = new Peterson();
    }

    public void increaseLevel(int id) {
        if(id == 0){
            peteRiver.preProtocole0();
            peteDamnRivers.preProtocole0();
            waterLevel++;
            peteDamnRivers.postProtocole0();
            peteRiver.postProtocole0();
        } else {
            peteRiver.preProtocole1();
            peteDamnRivers.preProtocole0();
            waterLevel++;
            peteDamnRivers.postProtocole0(); // rivers has id == 0;
            peteRiver.postProtocole1();
        }
    }

    public void decreaseLevel(int id){
        if (id == 0){
            peteDam.preProtocole0();
            while (waterLevel == 0){
                System.out.println("Currently the dam " + id + " is waiting to decrease the water level.");
                Thread.yield();
            }
            peteDamnRivers.preProtocole1(); // dams has ids == 0;
            waterLevel--;
            System.out.println("The dam " + id + " let the lake with " + waterLevel);
            if (waterLevel < 0){
                System.err.println("ERROR: Illegal decrease!");
                System.exit(-1);
            }
            peteDamnRivers.postProtocole1();
            peteDam.postProtocole0();
        } else {
            peteDam.preProtocole1();
            while (waterLevel == 0){
                System.out.println("Currently the dam " + id + " is waiting to decrease the water level.");
                Thread.yield();
            }
            peteDamnRivers.preProtocole1(); // dams has ids == 0;
            waterLevel--;
            System.out.println("The dam " + id + " let the lake with " + waterLevel);
            if (waterLevel < 0){
                System.err.println("ERROR: Illegal decrease!");
                System.exit(-1);
            }
            peteDamnRivers.postProtocole1();
            peteDam.postProtocole1();
        }
    }
    public int getWaterLevel(){
        return this.waterLevel;
    }
}
