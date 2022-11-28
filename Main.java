/**
 * @author Andressa, Gabriel, Roberto
 */

public class Main {
    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();
        ArvoreAvl arvore1 = new ArvoreAvl();

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

        System.out.println("contains 5? : " + clone.contains(5));
        System.out.println("contains 12? : " + clone.contains(12));

        
        arvore.inserir(1);
        arvore.inserir(2);
        arvore.inserir(3);
        arvore.inserir(4);
        arvore.inserir(5);
        arvore.inserir(6);
        arvore.inserir(7);
        arvore.inserir(8);
        arvore.inserir(9);

        arvore1.inserir(1);
        arvore1.inserir(2);
        arvore1.inserir(3);
        arvore1.inserir(4);
        arvore1.inserir(5);
        arvore1.inserir(6);
        arvore1.inserir(7);
        arvore1.inserir(8);
        arvore1.inserir(9);

        // Verifica se a arvore1 é espelho de arvore (Teste 3)
        System.out.println(arvore.isMirror(arvore1));
        arvore.GeraDOT();
        arvore1.GeraDOT();
        arvore1.clear();

        arvore1.inserir(9);
        arvore1.inserir(8);
        arvore1.inserir(7);
        arvore1.inserir(6);
        arvore1.inserir(5);
        arvore1.inserir(4);
        arvore1.inserir(3);
        arvore1.inserir(2);
        arvore1.inserir(1);

        // Verifica se a arvore1 é espelho de arvore (Teste 3)
        System.out.println(arvore.isMirror(arvore1));
        arvore.GeraDOT();
        arvore1.GeraDOT();
    }
}
