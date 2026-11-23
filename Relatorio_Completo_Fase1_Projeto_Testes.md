# Projeto de Testes de Software --- Biblioteca

## Relatório de atividades realizadas --- Fase 1 (Teste Caixa-Preta)

**Finalidade deste documento:** apresentar ao grupo o trabalho
desenvolvido até o momento, relacionar as atividades aos requisitos do
professor e orientar a revisão antes da entrega.

> **Situação:** documento de acompanhamento. A execução mais recente foi
> informada como 79 testes, 33 falhas, 0 erros e 0 ignorados. As falhas
> são resultados observados e precisam ser conferidas contra o Anexo A;
> não devem ser tratadas automaticamente como defeitos confirmados sem
> essa validação.

------------------------------------------------------------------------

## 1. Objetivo da atividade

A primeira fase do trabalho consiste em testar a biblioteca fornecida
usando a abordagem de **caixa-preta**. Isso significa elaborar os testes
a partir do comportamento descrito no **Anexo A**, sem consultar o
código-fonte interno da biblioteca nem descompilar o arquivo
`biblioteca-1.0.jar` para descobrir sua implementação.

O trabalho busca verificar se os métodos disponibilizados pela
biblioteca se comportam conforme a especificação. Para isso, foram
utilizadas duas técnicas de projeto de testes:

-   **Particionamento em classes de equivalência:** separar entradas em
    grupos que, segundo a especificação, devem apresentar comportamento
    semelhante.
-   **Análise de valores-limite:** testar valores próximos aos limites
    em que o comportamento pode mudar, como quantidade máxima de
    empréstimos, número de renovações, dias de atraso e limites de
    multa.

A regra adotada foi manter os resultados esperados baseados no Anexo A.
Uma falha não deve ser "corrigida" mudando o resultado esperado apenas
para fazer o teste passar.

## 2. Entregáveis solicitados para a Fase 1

O trabalho foi organizado para atender aos seguintes itens:

  ------------------------------------------------------------------------------------------------------------
  Entregável              Finalidade              Arquivo
  ----------------------- ----------------------- ------------------------------------------------------------
  Testes automatizados    Implementar os casos de `src/test/java/br/edu/biblioteca/Fase1CaixaPretaTest.java`
                          teste caixa-preta com   
                          JUnit 5                 

  Plano de testes         Registrar classes de    `PLANO-DE-TESTES.md`
                          equivalência,           
                          valores-limite e os     
                          testes relacionados     

  Registro de falhas      Documentar as           `DEFEITOS.md`
                          diferenças observadas   
                          entre o esperado e o    
                          obtido                  

  Declaração de uso de IA Informar como a         `USO-DE-IA.md`
                          ferramenta de IA foi    
                          utilizada e o que       
                          precisa de revisão      
                          humana                  
  ------------------------------------------------------------------------------------------------------------

O arquivo `ExemploTest.java` já fazia parte do kit fornecido e foi
mantido. Ele contém um teste de exemplo que verifica o prazo de
empréstimo de um aluno.

## 3. Ambiente e organização do projeto

O projeto utiliza Maven e JUnit 5. A execução é feita no terminal,
dentro da pasta que contém o arquivo `pom.xml`.

Estrutura esperada:

``` text
kit-fase1/
├── lib/
│   └── biblioteca-1.0.jar
├── src/
│   └── test/
│       └── java/
│           └── br/
│               └── edu/
│                   └── biblioteca/
│                       ├── ExemploTest.java
│                       └── Fase1CaixaPretaTest.java
├── PLANO-DE-TESTES.md
├── DEFEITOS.md
├── USO-DE-IA.md
├── pom.xml
└── .gitignore
```

**Comando utilizado para executar os testes:**

``` bash
mvn test
```

O Maven gera os relatórios individuais no diretório:

``` text
target/surefire-reports/
```

O relatório da suíte principal pode ser consultado em:

``` text
target/surefire-reports/br.edu.biblioteca.Fase1CaixaPretaTest.txt
```

## 4. Regras da especificação consideradas

A suíte foi elaborada para os sete métodos descritos no Anexo A:

  ------------------------------------------------------------------------------------------------
  Método                                                       Comportamento especificado que
                                                               orienta os testes
  ------------------------------------------------------------ -----------------------------------
  `prazoDias(tipo)`                                            Retorna 14 dias para aluno, 30 para
                                                               professor e 7 para visitante. Tipo
                                                               nulo deve gerar
                                                               `IllegalArgumentException`.

  `dataDevolucao(retirada, tipo)`                              Soma o prazo correspondente à data
                                                               de retirada. Se a data resultante
                                                               for domingo, passa para
                                                               segunda-feira. Sábado permanece.
                                                               Data de retirada nula ou tipo nulo
                                                               deve gerar
                                                               `IllegalArgumentException`.

  `limiteEmprestimos(tipo)`                                    Retorna limite 3 para aluno, 8 para
                                                               professor e 1 para visitante. Tipo
                                                               nulo deve gerar
                                                               `IllegalArgumentException`.

  `multaCentavos(tipo, diasAtraso)`                            Professor tem carência de 3 dias;
                                                               os demais tipos pagam desde o
                                                               primeiro dia. Os primeiros 10 dias
                                                               cobrados custam 100 centavos por
                                                               dia e os dias seguintes, 200
                                                               centavos por dia. O teto por
                                                               empréstimo é 5.000 centavos. Dias
                                                               negativos ou tipo nulo devem gerar
                                                               `IllegalArgumentException`.

  `podeEmprestar(tipo, ativos, multaPendente, possuiAtraso)`   Só permite empréstimo se a
                                                               quantidade de empréstimos ativos
                                                               estiver abaixo do limite, a multa
                                                               pendente for de no máximo 1.000
                                                               centavos e não houver atraso.
                                                               Valores negativos de empréstimos
                                                               ativos ou multa pendente e tipo
                                                               nulo devem gerar
                                                               `IllegalArgumentException`.

  `podeRenovar(tipo, renovacoes, possuiReserva, emAtraso)`     Visitante nunca pode renovar. Aluno
                                                               pode renovar até 2 vezes e
                                                               professor até 3, desde que não
                                                               exista reserva e não esteja em
                                                               atraso. Número negativo de
                                                               renovações ou tipo nulo deve gerar
                                                               `IllegalArgumentException`.

  `multaTotalCentavos(tipo, diasAtrasoPorEmprestimo)`          Soma as multas individuais,
                                                               respeitando o teto total de 10.000
                                                               centavos. Array vazio retorna zero.
                                                               Array nulo, elemento negativo ou
                                                               tipo nulo devem gerar
                                                               `IllegalArgumentException`.
  ------------------------------------------------------------------------------------------------

## 5. Estratégia de testes aplicada

Foram preparados testes para entradas válidas e inválidas, os três tipos
de usuário (`ALUNO`, `PROFESSOR` e `VISITANTE`) e condições de fronteira
relevantes.

### 5.1 Prazos e limites por tipo de usuário

Foram testados os valores específicos de cada tipo de usuário e o
comportamento para tipo nulo. A intenção é verificar tanto as classes
válidas (cada tipo enumerado) quanto a entrada inválida prevista na
especificação.

### 5.2 Cálculo de datas de devolução

Foram considerados os prazos dos três tipos de usuário e datas que
permitem observar: - Resultado em dia comum. - Resultado em domingo, que
deve ser deslocado para segunda-feira. - Resultado em sábado, que deve
permanecer no sábado. - Data de retirada nula. - Tipo de usuário nulo.

### 5.3 Cálculo de multa individual

Foram incluídos dias de atraso próximos às mudanças de faixa, casos de
carência do professor e casos próximos ao teto de 5.000 centavos. Também
foram testados dias negativos e tipo nulo.

### 5.4 Permissão para novo empréstimo

Foram testadas quantidades abaixo e no limite de empréstimos de cada
tipo, multa pendente no limite e acima dele, situação com atraso e
entradas negativas. Assim, os testes verificam as condições que precisam
ser satisfeitas simultaneamente para permitir o empréstimo.

### 5.5 Permissão para renovação

Foram testados números de renovações abaixo, no limite e acima do limite
para aluno e professor, além do caso de visitante. Também foram
verificadas as condições de existência de reserva, atraso, valor
negativo e tipo nulo.

### 5.6 Soma de multas

Foram incluídos array vazio, valores individuais para diferentes tipos,
soma de mais de um empréstimo, total exatamente no teto, total acima do
teto, array nulo, elemento negativo e tipo nulo.

Os casos concretos e seus nomes de métodos estão registrados no arquivo
Java e devem ser comparados com o mapeamento de `PLANO-DE-TESTES.md`.

## 6. Resultado da execução mais recente

Resultado informado pelo terminal após executar `mvn test`:

``` text
Tests run: 79, Failures: 33, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Resumo:

  Indicador             Quantidade
  ------------------- ------------
  Testes executados             79
  Testes aprovados              46
  Falhas                        33
  Erros de execução              0
  Ignorados                      0

A contagem foi explicada pela suíte principal, com 78 execuções de casos
de teste (incluindo os casos parametrizados), mais 1 teste de exemplo em
`ExemploTest.java`.

**Atenção ao `BUILD SUCCESS`:** o projeto está configurado para ignorar
falhas de testes no resultado do build. Portanto, `BUILD SUCCESS` não
significa que os 79 testes passaram. O resultado real deve ser lido pela
quantidade de testes, falhas, erros e ignorados.

As 33 falhas foram registradas no `DEFEITOS.md` com: - Método testado. -
Entrada utilizada. - Resultado esperado conforme o Anexo A. - Resultado
obtido na execução. - Classe de equivalência ou limite que motivou o
caso.

O relatório precisa ser conferido novamente sempre que os testes forem
modificados ou executados com uma versão diferente do projeto.

## 7. Cuidados para classificar uma falha como defeito

Uma falha de teste é uma divergência entre o resultado esperado pelo
teste e o resultado real da execução. Antes de registrá-la como defeito
da biblioteca, a equipe deve:

1.  Conferir se o resultado esperado foi interpretado corretamente do
    Anexo A.
2.  Conferir se a entrada do teste representa o caso que se pretendia
    testar.
3.  Verificar se o teste foi implementado corretamente.
4.  Confirmar que não houve erro de compilação, configuração ou
    ambiente.
5.  Reexecutar a suíte e conferir o relatório do Surefire.

Se o teste estiver correto e o resultado da biblioteca divergir do Anexo
A, a falha pode ser registrada como defeito observado. Não se deve
modificar o esperado apenas para reduzir o número de falhas.

## 8. Uso de inteligência artificial

O arquivo `USO-DE-IA.md` declara o uso do ChatGPT como apoio para: -
Compreender o enunciado. - Identificar possíveis classes de equivalência
e valores-limite a partir do Anexo A. - Estruturar o plano de testes. -
Elaborar uma implementação inicial de exemplos de testes JUnit 5.

A equipe continua responsável por revisar os casos, confirmar os
resultados esperados, executar os testes, analisar as falhas e atualizar
a documentação para refletir o que realmente foi aproveitado e alterado.

A declaração deve ser verdadeira e corresponder ao uso efetivo da
ferramenta por todos os integrantes.

## 9. Revisão solicitada ao grupo

Antes de considerar a Fase 1 pronta, revisar os seguintes itens:

-   [ ] Conferir cada teste com a regra correspondente do Anexo A.
-   [ ] Conferir as classes de equivalência válidas e inválidas no
    plano.
-   [ ] Conferir se os valores-limite escolhidos realmente exercitam as
    fronteiras especificadas.
-   [ ] Conferir se todos os nomes de testes citados no plano existem no
    arquivo Java.
-   [ ] Conferir se os casos implementados cobrem o que está descrito no
    plano.
-   [ ] Conferir as 33 falhas do `DEFEITOS.md` com o relatório mais
    recente do Surefire.
-   [ ] Confirmar que as falhas registradas não são causadas por testes
    incorretos.
-   [ ] Revisar se `USO-DE-IA.md` representa fielmente o trabalho
    realizado.
-   [ ] Executar novamente `mvn test` e guardar o resultado.
-   [ ] Verificar se o ZIP contém a pasta do projeto, o JAR fornecido,
    os testes, os documentos e o `pom.xml`.
-   [ ] Confirmar que nenhum resultado esperado foi obtido consultando
    ou descompilando o JAR.

## 10. Sugestão de divisão da revisão

Para facilitar o trabalho em grupo, a revisão pode ser dividida:

  -----------------------------------------------------------------------
  Responsável                         Atividade
  ----------------------------------- -----------------------------------
  Integrante A                        Conferir os testes Java e seus
                                      resultados esperados com o Anexo A.

  Integrante B                        Revisar classes de equivalência,
                                      valores-limite e mapeamento no
                                      plano.

  Integrante C                        Conferir cada falha registrada com
                                      o relatório do Maven.

  Todos                               Revisar a declaração de IA,
                                      executar os testes e aprovar a
                                      versão final.
  -----------------------------------------------------------------------

## 11. Uso do Git

Antes de enviar alterações, verificar o estado do repositório:

``` bash
git status
git diff
```

Adicionar e registrar as alterações:

``` bash
git add .
git commit -m "Adiciona testes caixa-preta e documentação da Fase 1"
```

Enviar para o repositório remoto já configurado:

``` bash
git push
```

Para obter alterações feitas por outro integrante:

``` bash
git pull
```

O diretório `target/` é gerado pelo Maven e normalmente não precisa ser
versionado; verificar se está contemplado no `.gitignore`. Evitar que
integrantes alterem simultaneamente os mesmos arquivos sem combinar a
integração.

## 12. Conclusão e situação atual

Até o momento, foram preparados: - Uma suíte JUnit 5 que exercita os
sete métodos da especificação. - Um plano de testes com classes de
equivalência e valores-limite. - Um documento de registro das falhas
observadas. - Uma declaração de uso de IA.

A última execução informada apresentou 79 testes, 33 falhas, 0 erros e 0
ignorados. Isso indica que a suíte foi executada e encontrou
divergências que precisam ser analisadas. A conclusão sobre a correção
de cada teste e a confirmação dos defeitos depende da revisão contra o
Anexo A e o relatório atualizado.

**Critério para finalizar:** entregar testes e documentação coerentes
entre si e com a especificação, com as falhas observadas justificadas e
registradas --- não simplesmente obter uma execução sem falhas.
