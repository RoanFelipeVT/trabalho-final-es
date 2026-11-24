# DEFEITOS ENCONTRADOS

**Execução dos testes:** 84 testes executados, 31 falhas, 0 erros e 0 testes ignorados.

## 1. `prazoDias()`

| Teste            | Entrada   | Esperado |  Obtido | Classe ou limite         |
| ---------------- | --------- | -------: | ------: | ------------------------ |
| `prazoVisitante` | VISITANTE |   7 dias | 10 dias | Classe válida: visitante |

## 2. `dataDevolucao()`

| Teste                       | Entrada               | Esperado   | Obtido     | Classe ou limite                   |
| --------------------------- | --------------------- | ---------- | ---------- | ---------------------------------- |
| `devolucaoVisitante`        | 01/01/2026, VISITANTE | 08/01/2026 | 11/01/2026 | Classe válida: visitante           |
| `devolucaoProfessorDomingo` | 02/01/2026, PROFESSOR | 02/02/2026 | 01/02/2026 | Valor-limite: resultado em domingo |

## 3. `multaCentavos()`

| Teste                | Entrada        | Esperado                 | Obtido          | Classe ou limite                |
| -------------------- | -------------- | ------------------------ | --------------- | ------------------------------- |
| `multaDiasNegativos` | ALUNO, -1 dia  | IllegalArgumentException | Nenhuma exceção | Classe inválida: dias negativos |
| `multaOnzeDias`      | ALUNO, 11 dias | 1200                     |                 |                                 |

# Defeitos encontrados



**Execução dos testes:** 84 testes executados, 31 falhas, 0 erros e 0 testes ignorados.



## 1. `prazoDias()`

- `prazoVisitante`: retorna 10 dias em vez de 7.



## 2. `dataDevolucao()`

- `devolucaoVisitante`: retorna 11/01/2026 em vez de 08/01/2026.

- `devolucaoProfessorDomingo`: não transfere a devolução de domingo para segunda-feira.



## 3. `multaCentavos()`

- `multaDiasNegativos`: não lança `IllegalArgumentException` para dias negativos.

- `multaNegativa`: não lança `IllegalArgumentException` para dias negativos.

- `multaOnzeDias`: calcula 1100 em vez de 1200 centavos.

- `multaVisitanteOnzeDias`: calcula 1100 em vez de 1200 centavos.

- `multaProfessorTresDias`: cobra 100 centavos, embora exista tolerância de três dias.

- `multaProfessorQuatroDias`: cobra 200 em vez de 100 centavos.

- `multaProfessorTrezeDias`: calcula 1100 em vez de 1000 centavos.

- `multaProfessorQuatorzeDias`: calcula 1400 em vez de 1200 centavos.

- `multaProfessorTrintaEDoisDias`: calcula 5000 em vez de 4800 centavos.

- `multaProfessorTrintaETresDias`: calcula 5200 em vez de 5000 centavos.

- `multaTrintaEUmDias`: calcula 5200 em vez de 5000 centavos.



## 4. `podeEmprestar()`

- `emprestimosNoLimiteAluno`: permite empréstimo mesmo quando o aluno atingiu o limite.

- `visitanteNoLimite`: permite empréstimo mesmo quando o visitante atingiu o limite.

- `professorNoLimite`: permite empréstimo mesmo quando o professor atingiu o limite.

- `usuarioComAtraso`: permite empréstimo para usuário com atraso.

- `atrasoMesmoComOutrasCondicoesValidas`: não bloqueia o empréstimo quando existe atraso.

- `multaAcimaDoLimite`: permite empréstimo mesmo quando a multa pendente ultrapassa o limite permitido.



## 5. `podeRenovar()`

- `visitanteNaoPodeRenovar`: permite renovação para visitantes.

- `professorTresRenovacoes`: permite renovação após o limite de três renovações.



## 6. `multaTotalCentavos()`

- `multaTotalElementoNegativoNoInicio`: não lança exceção para elemento negativo no início do vetor.

- `multaTotalElementoNegativoNoFinal`: não lança exceção para elemento negativo no final do vetor.

- `multaTotalAbaixoDoLimite`: retorna 4800 em vez de 9800 centavos.

- `multaTotalProfessorQuatroDias`: retorna 0 em vez de 100 centavos.

- `multaTotalVisitanteOnzeDias`: retorna 0 em vez de 1200 centavos.

- `multaTotalUmDia`: retorna 0 em vez de 100 centavos.

- `multaTotalAcimaDoLimite`: retorna 5100 em vez de 10000 centavos.

- `multaTotalExatamenteNoLimite`: retorna 5000 em vez de 10000 centavos.

- `multaTotalDoisEmprestimos`: retorna 200 em vez de 300 centavos.



## Resumo



Foram identificadas falhas relacionadas a prazos de empréstimo, cálculo de datas de devolução, cálculo e validação de multas, limites de empréstimos e regras de renovação.



As falhas indicam divergências entre o comportamento da biblioteca e os requisitos especificados.

|                                 | 1100               | Valor-limite: 11º dia cobrado |      |                                               |
| ------------------------------- | ------------------ | ----------------------------- | ---- | --------------------------------------------- |
| `multaVisitanteOnzeDias`        | VISITANTE, 11 dias | 1200                          | 1100 | Valor-limite: 11º dia cobrado                 |
| `multaProfessorTresDias`        | PROFESSOR, 3 dias  | 0                             | 100  | Valor-limite: fim da carência                 |
| `multaProfessorQuatroDias`      | PROFESSOR, 4 dias  | 100                           | 200  | Valor-limite: primeiro dia após a carência    |
| `multaProfessorTrezeDias`       | PROFESSOR, 13 dias | 1000                          | 1100 | Valor-limite: transição da faixa de cobrança  |
| `multaProfessorQuatorzeDias`    | PROFESSOR, 14 dias | 1200                          | 1400 | Classe válida: cobrança após a carência       |
| `multaProfessorTrintaEDoisDias` | PROFESSOR, 32 dias | 4800                          | 5000 | Valor-limite: proximidade do teto de R$ 50,00 |
| `multaProfessorTrintaETresDias` | PROFESSOR, 33 dias | 5000                          | 5200 | Valor-limite: teto de R$ 50,00                |
| `multaTrintaEUmDias`            | ALUNO, 31 dias     | 5000                          | 5200 | Valor-limite: teto de R$ 50,00                |

## 4. `podeEmprestar()`

| Teste                                  | Entrada                                       | Esperado                 | Obtido          | Classe ou limite                           |
| -------------------------------------- | --------------------------------------------- | ------------------------ | --------------- | ------------------------------------------ |
| `emprestimosNoLimiteAluno`             | ALUNO, 3 empréstimos, multa 0, sem atraso     | false                    | true            | Valor-limite: limite de 3 empréstimos      |
| `multaAcimaDoLimite`                   | ALUNO, 0 empréstimos, multa 1001, sem atraso  | false                    | true            | Valor-limite: multa acima de 1000 centavos |
| `visitanteNoLimite`                    | VISITANTE, 1 empréstimo, multa 0, sem atraso  | false                    | true            | Valor-limite: limite de 1 empréstimo       |
| `professorNoLimite`                    | PROFESSOR, 8 empréstimos, multa 0, sem atraso | false                    | true            | Valor-limite: limite de 8 empréstimos      |
| `usuarioComAtraso`                     | ALUNO, 0 empréstimos, multa 0, com atraso     | false                    | true            | Classe válida: usuário com atraso          |
| `atrasoMesmoComOutrasCondicoesValidas` | ALUNO, 2 empréstimos, multa 500, com atraso   | false                    | true            | Classe válida: usuário com atraso          |
| `multaNegativa`                        | ALUNO, 0 empréstimos, multa -1, sem atraso    | IllegalArgumentException | Nenhuma exceção | Classe inválida: multa negativa            |

## 5. `podeRenovar()`

| Teste                     | Entrada                                           | Esperado | Obtido | Classe ou limite                     |
| ------------------------- | ------------------------------------------------- | -------- | ------ | ------------------------------------ |
| `visitanteNaoPodeRenovar` | VISITANTE, 0 renovações, sem reserva e sem atraso | false    | true   | Classe válida: visitante             |
| `professorTresRenovacoes` | PROFESSOR, 3 renovações, sem reserva e sem atraso | false    | true   | Valor-limite: limite de 3 renovações |

## 6. `multaTotalCentavos()`

| Teste                                | Entrada            |                 Esperado |          Obtido | Classe ou limite                                 |
| ------------------------------------ | ------------------ | -----------------------: | --------------: | ------------------------------------------------ |
| `multaTotalElementoNegativoNoInicio` | ALUNO, [-1, 1]     | IllegalArgumentException | Nenhuma exceção | Classe inválida: elemento negativo               |
| `multaTotalElementoNegativoNoFinal`  | ALUNO, [1, -1]     | IllegalArgumentException | Nenhuma exceção | Classe inválida: elemento negativo               |
| `multaTotalAbaixoDoLimite`           | ALUNO, [30, 29]    |                     9800 |            4800 | Classe válida: total abaixo de 10000 centavos    |
| `multaTotalProfessorQuatroDias`      | PROFESSOR, [4]     |                      100 |               0 | Valor-limite: primeiro dia após a carência       |
| `multaTotalVisitanteOnzeDias`        | VISITANTE, [11]    |                     1200 |               0 | Valor-limite: 11º dia cobrado                    |
| `multaTotalUmDia`                    | ALUNO, [1]         |                      100 |               0 | Classe válida: um dia de atraso                  |
| `multaTotalAcimaDoLimite`            | ALUNO, [30, 30, 1] |                    10000 |            5100 | Valor-limite: total acima de 10000 centavos      |
| `multaTotalExatamenteNoLimite`       | ALUNO, [30, 30]    |                    10000 |            5000 | Valor-limite: total exatamente em 10000 centavos |
| `multaTotalDoisEmprestimos`          | ALUNO, [1, 2]      |                      300 |             200 | Classe válida: múltiplos empréstimos             |

## 7. Resumo

Foram identificadas **31 falhas** distribuídas entre os métodos testados.

Os defeitos encontrados envolvem:

- Prazos incorretos para determinados usuários.
- Cálculo incorreto de datas de devolução aos domingos.
- Erros no cálculo de multas, incluindo carência, faixas de cobrança e limites.
- Falhas na validação de entradas negativas.
- Desrespeito aos limites de empréstimos e renovações.
- Erros na soma e limitação das multas de múltiplos empréstimos.

As falhas foram identificadas por meio de testes derivados das classes de equivalência e da análise de valores-limite, conforme a especificação do sistema.
