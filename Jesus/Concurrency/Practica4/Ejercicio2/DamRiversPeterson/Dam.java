package DamRiversPeterson;

public class Dam extends Thread {
    private Lake lake;
    private int id;

    public Dam(Lake lake, int id){
        this.lake = lake;
        this.id = id;
    }

    public void run(){
        for(int i = 0; i < 100; i++){
            lake.decrease(id);
        }
    }
}
