# analisador-preditivo-tabular
Trabalho prático da disciplina de Linguagens de Programação

Entendimento do problema:

Desenvolver um analisador preditivo não-recursivo.

Levantamento e análise de Requisitos necessários:

Definição da linguagem de programação a ser usada:

JAVA.

Estrutura geral da solução conforme necessidades do trabalho:

Classe Main para inicializar o programa, Classe App onde são feito todas as operações e métodos necessários e Classe NaoTerminal que contêm o símbolo, as suas derivações e os seus FIRST e FOLLOW.

Com os FIRST e FOLLOW, iremos criar a tabela. Com a tabela, iremos verificar se uma sentença é reconhecida ou não com o autômato de pilha controlado pela tabela de análise.