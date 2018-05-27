public class Main {
  public static void main(String[] args) {
    try {
      Mesa mesa = new Mesa();
      Agente agente = new Agente(mesa);
      agente.start();
      Fumador[] fumador = new Fumador[Mesa.NINGR];
      for (int i = 0; i < fumador.length; ++i) {
        fumador[i] = new Fumador(mesa, i);
        fumador[i].start();
      }
      agente.join();
      for (int i = 0; i < fumador.length; ++i)
        fumador[i].join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
