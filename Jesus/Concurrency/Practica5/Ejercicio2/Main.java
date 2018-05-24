public class Main {
    public static void main (String [] args) {
        final int tapeSize = 6;
        Chain chain = new Chain(tapeSize);
        Setter setter = new Setter(chain);
        setter.start();
        Packager[] packers = new Packager[Chain.NUM_TYPES];

        for (int i=0; i<Chain.NUM_TYPES; i++) {
            packers[i] = new Packager(i, chain);
            packers[i].start();
        }
    }
}
