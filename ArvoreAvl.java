import java.util.ArrayList;

public class ArvoreAvl {
	protected No raiz;

	public int size(){
		return sizeAux(raiz);
	}

	private int sizeAux(No node){
	    if(node == null)
            return 0;
        else{
			return (sizeAux(node.getEsquerda()) + 1 + sizeAux(node.getDireita()));
		}
	}

	public void clear(){
		clearAux(raiz);
	}

	private void clearAux(No node){
		if(node == null)
            return;
		if(node.getEsquerda() == null && node.getDireita() == null){
			removerNoEncontrado(node);
			if(node.getPai() != null){
				clearAux(node.getPai());
			}
		}
		if(node.getEsquerda() != null){
			clearAux(node.getEsquerda());
		}
		if(node.getDireita()!= null){
			clearAux(node.getDireita());
		}
	}

  	public void inserir(int k) {
		No n = new No(k);
		inserirAVL(this.raiz, n);
	}

	public void inserirAVL(No aComparar, No aInserir) {
		if (aComparar == null) {
			this.raiz = aInserir;
		} else {
			if (aInserir.getChave() < aComparar.getChave()) { // add
				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);
				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}
			} else if (aInserir.getChave() > aComparar.getChave()) {
				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);
				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}
			} else {
				// O nó já existe
			}
		}
	}

	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();
		if (balanceamento == -2) {
			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);
			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}
		} else if (balanceamento == 2) {
			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);
			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}
		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(int k) {
		removerAVL(this.raiz, k);
	}

	public void removerAVL(No atual, int k) {
		if (atual == null) {
			throw new IllegalArgumentException();
		} else {
			if (atual.getChave() > k) {
				removerAVL(atual.getEsquerda(), k);
			} else if (atual.getChave() < k) {
				removerAVL(atual.getDireita(), k);
			} else if (atual.getChave() == k) {
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(No aRemover) { // remove
		No r;
		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {
			if (aRemover.getPai() == null) {
				this.raiz = null;
				return;
			}
			r = aRemover;
		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}
		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}
		if (p != null) {
			p.setPai(r.getPai());
		}
		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
	}

	public No rotacaoEsquerda(No inicial) {
		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());
		inicial.setDireita(direita.getEsquerda());
		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}
		direita.setEsquerda(inicial);
		inicial.setPai(direita);
		if (direita.getPai() != null) {
			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}
		setBalanceamento(inicial);
		setBalanceamento(direita);
		return direita;
	}

	public No rotacaoDireita(No inicial) {
		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());
		inicial.setEsquerda(esquerda.getDireita());
		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}
		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);
		if (esquerda.getPai() != null) {
			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}
		setBalanceamento(inicial);
		setBalanceamento(esquerda);
		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(No atual) {  // height
		if (atual == null) {
			return -1;
		}
		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}
	public int getAltura() {
		return altura(raiz);
	}

	// FIZ DAQUI  -----------------------------------------------------------------------------------------------------
	private boolean isEmpty(){
		if (altura(raiz) == -1){
			return true;
		}else{
			return false;
		}
	}

	private No getParent(No n){
		return n.getPai();
	}

	public ArrayList positionsPre() {
    	ArrayList res = new ArrayList<>();
        positionsPreAux(raiz, res);
        return res;
    }

    private void positionsPreAux(No n, ArrayList res) {
        if (n != null) {
            res.add(n); //Visita o nodo
            positionsPreAux(n.getEsquerda(), res); //Visita a subárvore da esquerda
            positionsPreAux(n.getDireita(), res); //Visita a subárvore da direita
        }
    }

    public ArrayList positionsPos() {
        ArrayList res = new ArrayList<>();
        positionsPosAux(raiz, res);
        return res;
    }

    private void positionsPosAux(No n, ArrayList res) {
        if (n != null) {
            positionsPosAux(n.getEsquerda(), res); //Visita a subárvore da esquerda
            positionsPosAux(n.getDireita(), res); //Visita a subárvore da direita
            res.add(n); //Visita o nodo
        }
    }

    public ArrayList positionsCentral() {
        ArrayList res = new ArrayList<>();
        positionsCentralAux(raiz, res);
        return res;
    }

    private void positionsCentralAux(No n, ArrayList res) {
        if (n != null) {
            positionsCentralAux(n.getEsquerda(), res); //Visita a subárvore da esquerda
            res.add(n); //Visita o nodo
            positionsCentralAux(n.getDireita(), res); //Visita a subárvore da direita
        }
    }

    public ArrayList positionsWidth() {
        Queue<No> fila = new Queue<>();
        No atual = null;
    	ArrayList res = new ArrayList<>();
        if (raiz != null) {
            fila.enqueue(raiz);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.getEsquerda() != null) {
                    fila.enqueue(atual.getEsquerda());
                }
                if (atual.getDireita() != null) {
                    fila.enqueue(atual.getDireita());
                }
                res.add(atual);
            }
        }
        return res;
    }

	private void GeraConexoesDOT(No nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.getEsquerda());
        if (nodo.getEsquerda() != null) {
            System.out.println("\"node" + nodo + "\":esq -> \"node" + nodo.getEsquerda() + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.getDireita());
        if (nodo.getDireita() != null) {
            System.out.println("\"node" + nodo + "\":dir -> \"node" + nodo.getDireita() + "\" " + "\n");
        }
    }

    private void GeraNodosDOT(No nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.getEsquerda());
        System.out.println("node" + nodo + "[label = \"<esq> | " + nodo + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.getDireita());
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(raiz);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(raiz);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(raiz);
        System.out.println("}" + "\n");
    }

	// ATÉ AQUI ------------------------------------------------------------------------------------------

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	final protected ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorder(raiz, ret);
		return ret;
	}

	final protected void inorder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getDireita(), lista);
	}
}