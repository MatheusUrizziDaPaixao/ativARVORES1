public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
        System.out.println("Árvore Binária criada com sucesso!");
    }

    public void remover(Integer conteudo) {
        if (estaVazia()) {
            System.out.println("A árvore está vazia. Não há nós para remover.");
            return;
        }
        this.raiz = removerRecursivo(this.raiz, conteudo);
    }

    private No removerRecursivo(No atual, Integer conteudo) {
        if (atual == null) {
            System.out.println("O nó " + conteudo + " não foi encontrado na árvore.");
            return null;
        }

        if (conteudo < atual.getConteudo()) {
            atual.setEsquerda(removerRecursivo(atual.getEsquerda(), conteudo));
        } else if (conteudo > atual.getConteudo()) {
            atual.setDireita(removerRecursivo(atual.getDireita(), conteudo));
        } else {
            if (atual.getEsquerda() == null && atual.getDireita() == null) {
                System.out.println("O nó " + conteudo + " era folha e foi removido.");
                return null;
            }

            if (atual.getEsquerda() == null) {
                System.out.println("O nó " + conteudo + " foi removido e substituído pelo filho direito.");
                return atual.getDireita();
            }

            if (atual.getDireita() == null) {
                System.out.println("O nó " + conteudo + " foi removido e substituído pelo filho esquerdo.");
                return atual.getEsquerda();
            }

            No sucessor = encontrarSucessor(atual.getDireita());
            System.out.println("O nó " + conteudo + " foi removido e substituído pelo sucessor " + sucessor.getConteudo() + ".");
            atual.setConteudo(sucessor.getConteudo());
            atual.setDireita(removerRecursivo(atual.getDireita(), sucessor.getConteudo()));
        }

        return atual;
    }

    private No encontrarSucessor(No no) {
        No atual = no;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

//    public void inserir(Integer conteudo) {
//        No novoNo = new No(conteudo);
//
//        if(estaVazia()) {
//            this.raiz = novoNo;
//        } else {
//            No aux = this.raiz;
//            while(true) {
//                if (aux.getConteudo() > novoNo.getConteudo()) {
//                    if (aux.getEsquerda() == null) {
//                        aux.setEsquerda(novoNo);
//                        return;
//                    } else {
//                        aux = aux.getEsquerda();
//                    }
//                } else if (aux.getConteudo() == novoNo.getConteudo()) {
//                    System.out.println("Não é possível informar nós repetidos.");
//                    return;
//                } else {
//                    if (aux.getDireita() == null) {
//                        aux.setDireita(novoNo);
//                        return;
//                    } else {
//                        aux = aux.getDireita();
//                    }
//                }
//            }
//        }
//    }

    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);

        if(estaVazia()) {
            this.raiz = novoNo;
        } else {
            inserirRecursivo(novoNo, this.raiz);
        }
    }

    public void inserirRecursivo(No novoNo, No atual) {
        if (atual.getConteudo() > novoNo.getConteudo()) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(novoNo);
                System.out.println("O nó " + novoNo.getConteudo() + " foi inserido na Árvore.");
                return;
            } else {
                inserirRecursivo(novoNo, atual.getEsquerda());
            }
        } else if (atual.getConteudo() == novoNo.getConteudo()) {
            System.out.println("Não é possível informar nós repetidos.");
            return;
        } else {
            if (atual.getDireita() == null) {
                atual.setDireita(novoNo);
                System.out.println("O nó " + novoNo.getConteudo() + " foi inserido na Árvore.");
                return;
            } else {
                inserirRecursivo(novoNo, atual.getDireita());
            }
        }
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    private void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getConteudo());
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsquerda());
        System.out.println(no.getConteudo());
        emOrdem(no.getDireita());
    }

    private void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.println(no.getConteudo());
    }

    public void exibir(String percurso) {
        switch (percurso){
            case("Pre"):
                preOrdem(this.raiz);
                break;
            case("Em"):
                emOrdem(this.raiz);
                break;
            case("Pos"):
                posOrdem(this.raiz);
                break;
        }
    }


}
