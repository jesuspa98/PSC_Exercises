package DamRiversPeterson;

public class River extends Thread{
    private Lake lake;
    private int id;

    public River(Lake lake, int id){
        this.lake = lake;
        this.id = id;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            lake.increase(id);
        }
    }
}
