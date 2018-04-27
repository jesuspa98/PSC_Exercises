package DamRiversPeterson;

import Peterson2.Peterson2;

public class Lake {
    private int level;
    Peterson2 petersonRiver, petersonDam, petersonRiverDam;

    public Lake(){
        level = 0;
        petersonDam = new Peterson2();
        petersonRiver = new Peterson2();
        petersonRiverDam = new Peterson2();
    }

    public void increase(int idRiver){
        if(idRiver == 0){
            petersonRiver.preProtocol0();
            petersonRiverDam.preProtocol0();
            level++;
            System.out.println("River " + idRiver + " has " + level + " level");
            petersonRiverDam.postProtocol0();
            petersonRiver.postProtocol0();
        }else{
            petersonRiver.preProtocol1();
            petersonRiverDam.preProtocol0();
            level++;
            System.out.println("River " + idRiver + " has " + level + " level");
            petersonRiverDam.postProtocol0();
            petersonRiver.postProtocol1();
        }
    }

    public void decrease(int idDam){
        if(idDam == 0){
            petersonDam.preProtocol0();
            while(level == 0){
                System.out.println("Dam " + idDam + " is waiting");
                Thread.yield();
            }
            petersonRiverDam.preProtocol1();
            level--;
            System.out.println("Dam " + idDam + " has " + level + " level");
            if(level < 0){
                System.out.println("ILEGAL DAM " + idDam + "DECREASE");
                System.exit(-1);
            }
            petersonRiverDam.postProtocol1();
            petersonDam.postProtocol0();
        }else{
            petersonDam.preProtocol1();
            while(level == 0){
                System.out.println("Dam " + idDam + " is waiting");
                Thread.yield();
            }
            petersonRiverDam.preProtocol1();
            level--;
            System.out.println("Dam " + idDam + " has " + level + " level");
            if(level < 0){
                System.out.println("ILEGAL DAM " + idDam + "DECREASE");
                System.exit(-1);
            }
            petersonRiverDam.postProtocol1();
            petersonDam.postProtocol1();
        }
    }

    public int getLevel() {
        return level;
    }
}
