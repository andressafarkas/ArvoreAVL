/**
 * @author Andressa, Gabriel, Roberto
 */

public class Main {
    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();

        arvore.inserir(1);
        arvore.inserir(2);
        arvore.inserir(3);
        arvore.inserir(4);
        arvore.inserir(5);
        arvore.inserir(6);
        arvore.inserir(7);
        arvore.inserir(8);
        arvore.inserir(9);

        System.out.println("Altura: " + arvore.getAltura());
        arvore.GeraDOT();
        arvore.clear();


        arvore.inserir(9);
        arvore.inserir(8);
        arvore.inserir(7);
        arvore.inserir(6);
        arvore.inserir(5);
        arvore.inserir(4);
        arvore.inserir(3);
        arvore.inserir(2);
        arvore.inserir(1);


        System.out.println("Conteúdo usando caminhamento central:");
        System.out.println(arvore.positionsCentral());

        ArvoreAvl clone = arvore.clone();
        clone.GeraDOT();

    }
}
