/**
 * @author Andressa, Gabriel, Roberto
 */

public class Main {
    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();

        arvore.inserir(1); // inserindo
        arvore.inserir(2);
        arvore.inserir(3);
        arvore.inserir(4);
        arvore.inserir(5);
        arvore.inserir(6);
        arvore.inserir(7);
        arvore.inserir(8);
        arvore.inserir(9);

        System.out.println("Altura: " + arvore.getAltura()); // altura
        System.out.println("Tamanho: " + arvore.size()); // tamanho

        arvore.GeraNodosDOT();
        arvore.GeraConexoesDOT();


        arvore.clear(); // limpa a lista
        System.out.println("clear");

        arvore.GeraNodosDOT(); // testa para ver se o clear limpou tudo

        arvore.inserir(9);
        arvore.inserir(8);
        arvore.inserir(7);
        arvore.inserir(6);
        arvore.inserir(5);
        arvore.inserir(4);
        arvore.inserir(3);
        arvore.inserir(2);
        arvore.inserir(1);

//        arvore.GeraConexoesDOT();
//
//        arvore.positionsCentral();
//
//        arvore.GeraNodosDOT();

    }
}
