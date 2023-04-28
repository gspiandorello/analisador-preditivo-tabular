# analisador-preditivo-tabular
Trabalho prático da disciplina de Linguagens de Programação

Entendimento do problema:

Desenvolver um analisador preditivo não-recursivo. Para isso, o usuário vai cadastrar uma linguagem, dizendo os símbolos não terminais e suas derivações. Com as derivações iremos fazer os FIRST e FOLLOW de cada não terminal e construir a tabela de análise preditiva tabular.
Com a tabela feita, o usuário irá digitar uma sentença e o programa vai dizer se essa sentença é válida ou não, mostrando o passo a passo do autômato de pilha controlado pela tabela de análise.

Levantamento e análise de Requisitos necessários:

Será necessário a criação de três classes: a Main, que irá inicializar o projeto, a App, para fazer as operações e métodos necessários e a NaoTerminal, onde vai conter o símbolo não terminal, suas derivações e os seus FIRST e FOLLOW.
Também será necessária a criação de duas ArrayLists para armazenar os não terminais e os terminais.

Definição da linguagem de programação a ser usada:

JAVA.

Estrutura geral da solução conforme necessidades do trabalho:

Classe Main para inicializar o programa, Classe App onde são feito todas as operações e métodos necessários e Classe NaoTerminal que contêm o símbolo, as suas derivações e os seus FIRST e FOLLOW.
Com os FIRST e FOLLOW, iremos criar a tabela. Com a tabela, iremos verificar se uma sentença é reconhecida ou não com o autômato de pilha controlado pela tabela de análise.