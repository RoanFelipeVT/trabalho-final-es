# PLANO-DE-TESTES.md

## 1. Objetivo

Verificar o comportamento da biblioteca `biblioteca-1.0.jar` por meio de testes automatizados de caixa-preta, comparando os resultados obtidos com os requisitos especificados no Anexo A.

## 2. Ambiente de testes

- Linguagem: Java 17
- Gerenciador de dependências: Maven
- Framework: JUnit 5
- Biblioteca testada: `biblioteca-1.0.jar`
- IDE: IntelliJ IDEA
- Tipo de teste: Caixa-preta

## 3. Métodos testados

Foram testados os sete métodos públicos da classe `RegrasBiblioteca`:

1. `prazoDias()`
2. `dataDevolucao()`
3. `limiteEmprestimos()`
4. `multaCentavos()`
5. `podeEmprestar()`
6. `podeRenovar()`
7. `multaTotalCentavos()`

## 4. Técnicas utilizadas

- **Particionamento de equivalência:** agrupamento das entradas em classes válidas e inválidas que devem apresentar comportamentos semelhantes.
- **Análise de valores-limite:** verificação de valores próximos aos limites definidos na especificação.
- **Casos especiais:** testes envolvendo domingos, virada de ano, vetores vazios e valores nulos.
- **Testes de exceção:** verificação das entradas inválidas que devem provocar `IllegalArgumentException`.

## 5. Classes de equivalência e valores-limite

### 5.1. `prazoDias(tipo)`

**Classes válidas:**

| Classe | Entrada | Resultado esperado |
|---|---|---:|
| Aluno | ALUNO | 14 dias |
| Professor | PROFESSOR | 30 dias |
| Visitante | VISITANTE | 7 dias |

**Classe inválida:**

| Entrada | Resultado esperado |
|---|---|
| `null` | `IllegalArgumentException` |

**Cobertura:** foram testados os três tipos de usuário e a entrada nula, totalizando 4 casos.

### 5.2. `dataDevolucao(retirada, tipo)`

**Classes válidas:**

| Classe | Entrada | Comportamento esperado |
|---|---|---|
| Aluno | Data válida e ALUNO | Adicionar 14 dias |
| Professor | Data válida e PROFESSOR | Adicionar 30 dias |
| Visitante | Data válida e VISITANTE | Adicionar 7 dias |
| Resultado em sábado | Data cujo resultado seja sábado | Manter sábado |
| Resultado em domingo | Data cujo resultado seja domingo | Transferir para segunda-feira |

**Classes inválidas:**

| Entrada | Resultado esperado |
|---|---|
| Data de retirada nula | `IllegalArgumentException` |
| Tipo nulo | `IllegalArgumentException` |

**Casos especiais e limites:** foram verificadas datas próximas à virada do ano e datas cujo resultado ocorre no fim de semana.

**Cobertura:** 13 casos de teste, incluindo os diferentes tipos de usuário, datas especiais e entradas inválidas.

### 5.3. `limiteEmprestimos(tipo)`

**Classes válidas:**

| Classe | Entrada | Resultado esperado |
|---|---|---:|
| Aluno | ALUNO | 3 empréstimos |
| Professor | PROFESSOR | 8 empréstimos |
| Visitante | VISITANTE | 1 empréstimo |

**Classe inválida:**

| Entrada | Resultado esperado |
|---|---|
| `null` | `IllegalArgumentException` |

**Cobertura:** foram testados os três tipos de usuário e a entrada nula, totalizando 4 casos.

### 5.4. `multaCentavos(tipo, diasAtraso)`

**Classes válidas:**

| Classe | Entrada | Comportamento esperado |
|---|---|---|
| Sem atraso | 0 dias | Multa de 0 centavos |
| Aluno | ALUNO, dias positivos | Cobrança desde o primeiro dia |
| Visitante | VISITANTE, dias positivos | Cobrança desde o primeiro dia |
| Professor em carência | PROFESSOR, até 3 dias | Multa de 0 centavos |
| Professor após carência | PROFESSOR, acima de 3 dias | Cobrança a partir do quarto dia |

**Valores-limite:**

| Limite | Entrada | Resultado esperado |
|---|---|---|
| Fim da carência | Professor, 3 dias | 0 centavos |
| Primeiro dia após carência | Professor, 4 dias | 100 centavos |
| Primeiros 10 dias cobrados | 10 dias de atraso | 1000 centavos |
| Transição da cobrança | 11 dias de atraso | 1200 centavos |
| Teto por empréstimo | Valor que atinja ou ultrapasse o limite | 5000 centavos |

A cobrança é de 100 centavos por dia nos primeiros 10 dias cobrados e de 200 centavos por dia a partir do 11º dia cobrado, limitada a 5000 centavos por empréstimo.

**Classes inválidas:**

| Entrada | Resultado esperado |
|---|---|
| Dias de atraso negativos | `IllegalArgumentException` |
| Tipo nulo | `IllegalArgumentException` |

**Cobertura:** 18 casos de teste, incluindo os três tipos de usuário, a carência dos professores, as faixas de cobrança, o teto da multa e entradas inválidas.

### 5.5. `podeEmprestar(tipo, emprestimosAtivos, multaPendenteCentavos, possuiAtraso)`

**Classes válidas:**

| Classe | Entrada ou condição | Resultado esperado |
|---|---|---|
| Abaixo do limite | Empréstimos ativos abaixo do limite do usuário | Pode emprestar, se as demais condições forem atendidas |
| Multa permitida | Multa pendente de até 1000 centavos | Pode emprestar, se as demais condições forem atendidas |
| Sem atraso | `possuiAtraso = false` | Pode emprestar, se as demais condições forem atendidas |

**Classes inválidas ou condições impeditivas:**

| Classe | Entrada ou condição | Resultado esperado |
|---|---|---|
| Empréstimos no limite | Aluno com 3, professor com 8 ou visitante com 1 | `false` |
| Multa acima do permitido | Multa de 1001 centavos | `false` |
| Possui atraso | `possuiAtraso = true` | `false` |
| Empréstimos negativos | Valor negativo | `IllegalArgumentException` |
| Multa negativa | Valor negativo | `IllegalArgumentException` |
| Tipo nulo | `null` | `IllegalArgumentException` |

**Valores-limite:** foram considerados os limites de 3, 8 e 1 empréstimo, conforme o tipo de usuário, e o limite de 1000 centavos de multa pendente.

**Cobertura:** 18 casos de teste, verificando os limites por tipo de usuário, as condições de bloqueio e as entradas inválidas.

### 5.6. `podeRenovar(tipo, renovacoesFeitas, possuiReserva, emAtraso)`

**Classes válidas:**

| Classe | Entrada ou condição | Resultado esperado |
|---|---|---|
| Aluno | Menos de 2 renovações | Pode renovar, se não houver reserva ou atraso |
| Professor | Menos de 3 renovações | Pode renovar, se não houver reserva ou atraso |

**Classes inválidas ou condições impeditivas:**

| Classe | Entrada ou condição | Resultado esperado |
|---|---|---|
| Visitante | VISITANTE | `false` |
| Aluno no limite | 2 renovações | `false` |
| Professor no limite | 3 renovações | `false` |
| Possui reserva | `possuiReserva = true` | `false` |
| Em atraso | `emAtraso = true` | `false` |
| Renovações negativas | Valor negativo | `IllegalArgumentException` |
| Tipo nulo | `null` | `IllegalArgumentException` |

**Valores-limite:** foram verificados os limites de 2 renovações para alunos e 3 para professores.

**Cobertura:** 14 casos de teste, considerando os tipos de usuário, os limites de renovação, reservas, atrasos e entradas inválidas.

### 5.7. `multaTotalCentavos(tipo, diasAtrasoPorEmprestimo)`

**Classes válidas:**

| Classe | Entrada ou condição | Resultado esperado |
|---|---|---|
| Vetor vazio | `[]` | 0 centavos |
| Um empréstimo | `[1]` | Multa correspondente a um dia |
| Múltiplos empréstimos | `[1, 2]` | Soma das multas individuais |
| Total abaixo do limite | Soma inferior a 10000 centavos | Retornar a soma |
| Total no limite | Soma de 10000 centavos | 10000 centavos |
| Total acima do limite | Soma superior a 10000 centavos | 10000 centavos |

**Classes inválidas:**

| Classe | Entrada | Resultado esperado |
|---|---|---|
| Vetor nulo | `null` | `IllegalArgumentException` |
| Elemento negativo no início | `[-1, 1]` | `IllegalArgumentException` |
| Elemento negativo no final | `[1, -1]` | `IllegalArgumentException` |
| Tipo nulo | `null` | `IllegalArgumentException` |

**Valores-limite:** foram verificados os limites de 10000 centavos para a multa total e os limites individuais de cobrança, incluindo a carência dos professores.

**Cobertura:** 13 casos de teste, incluindo vetores vazios, empréstimos individuais e múltiplos, limites de cobrança e entradas inválidas.

## 6. Distribuição dos casos de teste

Foram elaborados 84 casos de teste, distribuídos entre os sete métodos:

| Método | Casos de teste |
|---|---:|
| `prazoDias()` | 4 |
| `dataDevolucao()` | 13 |
| `limiteEmprestimos()` | 4 |
| `multaCentavos()` | 18 |
| `podeEmprestar()` | 18 |
| `podeRenovar()` | 14 |
| `multaTotalCentavos()` | 13 |
| **Total** | **84** |

## 7. Execução

Os testes foram implementados com JUnit 5 e executados utilizando o Maven:

```bash
mvn clean test
```

## 8. Resultados

Na execução registrada, foram obtidos:

- Testes executados: 84
- Testes aprovados: 53
- Testes com falha: 31
- Erros de execução: 0
- Testes ignorados: 0

As falhas identificadas foram registradas no arquivo `DEFEITOS.md`, contendo as entradas utilizadas, os resultados esperados, os resultados obtidos e as classes de equivalência ou valores-limite que motivaram cada teste.