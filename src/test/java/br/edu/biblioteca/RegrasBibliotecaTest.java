
package br.edu.biblioteca;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RegrasBibliotecaTest {

    private final RegrasBiblioteca regras = new RegrasBiblioteca();


    // =====================================================
    // 1. prazoDias
    // =====================================================

    @Test
    void prazoAluno() {
        assertEquals(14, regras.prazoDias(TipoUsuario.ALUNO));
    }

    @Test
    void prazoProfessor() {
        assertEquals(30, regras.prazoDias(TipoUsuario.PROFESSOR));
    }

    @Test
    void prazoVisitante() {
        assertEquals(7, regras.prazoDias(TipoUsuario.VISITANTE));
    }

    @Test
    void prazoTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.prazoDias(null));
    }


    // =====================================================
    // 2. dataDevolucao
    // =====================================================

    @Test
    void devolucaoAluno() {
        assertEquals(
                LocalDate.of(2026, 1, 15),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 1),
                        TipoUsuario.ALUNO)
        );
    }

    @Test
    void devolucaoProfessor() {
        assertEquals(
                LocalDate.of(2026, 1, 31),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 1),
                        TipoUsuario.PROFESSOR)
        );
    }

    @Test
    void devolucaoVisitante() {
        assertEquals(
                LocalDate.of(2026, 1, 8),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 1),
                        TipoUsuario.VISITANTE)
        );
    }

    @Test
    void devolucaoSexta() {
        assertEquals(
                LocalDate.of(2026, 1, 16),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 2),
                        TipoUsuario.ALUNO)
        );
    }

    @Test
    void devolucaoSabado() {
        assertEquals(
                LocalDate.of(2026, 1, 17),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 3),
                        TipoUsuario.ALUNO)
        );
    }

    @Test
    void devolucaoDomingo() {
        assertEquals(
                LocalDate.of(2026, 1, 19),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 4),
                        TipoUsuario.ALUNO)
        );
    }

    @Test
    void devolucaoSegunda() {
        assertEquals(
                LocalDate.of(2026, 1, 19),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 5),
                        TipoUsuario.ALUNO)
        );
    }

    @Test
    void devolucaoProfessorDomingo() {
        assertEquals(
                LocalDate.of(2026, 2, 2),
                regras.dataDevolucao(
                        LocalDate.of(2026, 1, 2),
                        TipoUsuario.PROFESSOR)
        );
    }

    @Test
    void devolucaoDataNula() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.dataDevolucao(
                        null, TipoUsuario.ALUNO));
    }

    @Test
    void devolucaoTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.dataDevolucao(
                        LocalDate.of(2026, 1, 1), null));
    }

    @Test
    void devolucaoViradaDeAno() {
        assertEquals(
                LocalDate.of(2027, 1, 4),
                regras.dataDevolucao(
                        LocalDate.of(2026, 12, 20),
                        TipoUsuario.ALUNO)
        );
    }


    // =====================================================
    // 3. limiteEmprestimos
    // =====================================================

    @Test
    void limiteAluno() {
        assertEquals(3, regras.limiteEmprestimos(TipoUsuario.ALUNO));
    }

    @Test
    void limiteProfessor() {
        assertEquals(8, regras.limiteEmprestimos(TipoUsuario.PROFESSOR));
    }

    @Test
    void limiteVisitante() {
        assertEquals(1, regras.limiteEmprestimos(TipoUsuario.VISITANTE));
    }

    @Test
    void limiteTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.limiteEmprestimos(null));
    }


    // =====================================================
    // 4. multaCentavos
    // =====================================================

    @Test
    void multaDiasNegativos() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.multaCentavos(TipoUsuario.ALUNO, -1));
    }

    @Test
    void multaZeroDias() {
        assertEquals(0, regras.multaCentavos(TipoUsuario.ALUNO, 0));
    }

    @Test
    void multaUmDia() {
        assertEquals(100, regras.multaCentavos(TipoUsuario.ALUNO, 1));
    }

    @Test
    void multaNoveDias() {
        assertEquals(900, regras.multaCentavos(TipoUsuario.ALUNO, 9));
    }

    @Test
    void multaDezDias() {
        assertEquals(1000, regras.multaCentavos(TipoUsuario.ALUNO, 10));
    }

    @Test
    void multaOnzeDias() {
        assertEquals(1200, regras.multaCentavos(TipoUsuario.ALUNO, 11));
    }

    @Test
    void multaVinteENoveDias() {
        assertEquals(4800, regras.multaCentavos(TipoUsuario.ALUNO, 29));
    }

    @Test
    void multaTrintaDias() {
        assertEquals(5000, regras.multaCentavos(TipoUsuario.ALUNO, 30));
    }

    @Test
    void multaTrintaEUmDias() {
        assertEquals(5000, regras.multaCentavos(TipoUsuario.ALUNO, 31));
    }

    @Test
    void multaProfessorDoisDias() {
        assertEquals(0, regras.multaCentavos(TipoUsuario.PROFESSOR, 2));
    }

    @Test
    void multaProfessorTresDias() {
        assertEquals(0, regras.multaCentavos(TipoUsuario.PROFESSOR, 3));
    }

    @Test
    void multaProfessorQuatroDias() {
        assertEquals(100, regras.multaCentavos(TipoUsuario.PROFESSOR, 4));
    }

    @Test
    void multaProfessorTrezeDias() {
        assertEquals(1000, regras.multaCentavos(TipoUsuario.PROFESSOR, 13));
    }

    @Test
    void multaProfessorQuatorzeDias() {
        assertEquals(1200, regras.multaCentavos(TipoUsuario.PROFESSOR, 14));
    }

    @Test
    void multaProfessorTrintaEDoisDias() {
        assertEquals(4800, regras.multaCentavos(TipoUsuario.PROFESSOR, 32));
    }

    @Test
    void multaProfessorTrintaETresDias() {
        assertEquals(5000, regras.multaCentavos(TipoUsuario.PROFESSOR, 33));
    }

    @Test
    void multaVisitanteOnzeDias() {
        assertEquals(1200, regras.multaCentavos(TipoUsuario.VISITANTE, 11));
    }

    @Test
    void multaTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.multaCentavos(null, 5));
    }


    // =====================================================
    // 5. podeEmprestar
    // =====================================================

    @Test
    void alunoPodeEmprestar() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.ALUNO, 2, 500, false));
    }

    @Test
    void alunoPodeEmprestarSemMulta() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.ALUNO, 0, 0, false));
    }

    @Test
    void emprestimosNoLimiteAluno() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.ALUNO, 3, 0, false));
    }

    @Test
    void emprestimosAcimaDoLimiteAluno() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.ALUNO, 4, 0, false));
    }

    @Test
    void multaAbaixoDoLimite() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.ALUNO, 0, 999, false));
    }

    @Test
    void multaExatamenteNoLimite() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.ALUNO, 0, 1000, false));
    }

    @Test
    void multaAcimaDoLimite() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.ALUNO, 0, 1001, false));
    }

    @Test
    void usuarioComAtraso() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.ALUNO, 0, 0, true));
    }

    @Test
    void professorAbaixoDoLimite() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.PROFESSOR, 7, 0, false));
    }

    @Test
    void professorNoLimite() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.PROFESSOR, 8, 0, false));
    }

    @Test
    void professorAcimaDoLimite() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.PROFESSOR, 9, 0, false));
    }

    @Test
    void visitanteAbaixoDoLimite() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.VISITANTE, 0, 0, false));
    }

    @Test
    void visitanteNoLimite() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.VISITANTE, 1, 0, false));
    }

    @Test
    void emprestimosNegativos() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.podeEmprestar(
                        TipoUsuario.ALUNO, -1, 0, false));
    }

    @Test
    void multaNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.podeEmprestar(
                        TipoUsuario.ALUNO, 0, -1, false));
    }

    @Test
    void podeEmprestarTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.podeEmprestar(
                        null, 0, 0, false));
    }

    @Test
    void atrasoMesmoComOutrasCondicoesValidas() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.ALUNO, 2, 500, true));
    }

    @Test
    void todasCondicoesImpedemEmprestimo() {
        assertFalse(regras.podeEmprestar(
                TipoUsuario.ALUNO, 3, 1001, true));
    }

    @Test
    void professorComMultaNoLimite() {
        assertTrue(regras.podeEmprestar(
                TipoUsuario.PROFESSOR, 0, 1000, false));
    }


    // =====================================================
    // 6. podeRenovar
    // =====================================================

    @Test
    void alunoZeroRenovacoes() {
        assertTrue(regras.podeRenovar(
                TipoUsuario.ALUNO, 0, false, false));
    }

    @Test
    void alunoUmaRenovacao() {
        assertTrue(regras.podeRenovar(
                TipoUsuario.ALUNO, 1, false, false));
    }

    @Test
    void alunoDuasRenovacoes() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.ALUNO, 2, false, false));
    }

    @Test
    void alunoTresRenovacoes() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.ALUNO, 3, false, false));
    }

    @Test
    void professorZeroRenovacoes() {
        assertTrue(regras.podeRenovar(
                TipoUsuario.PROFESSOR, 0, false, false));
    }

    @Test
    void professorDuasRenovacoes() {
        assertTrue(regras.podeRenovar(
                TipoUsuario.PROFESSOR, 2, false, false));
    }

    @Test
    void professorTresRenovacoes() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.PROFESSOR, 3, false, false));
    }

    @Test
    void professorQuatroRenovacoes() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.PROFESSOR, 4, false, false));
    }

    @Test
    void visitanteNaoPodeRenovar() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.VISITANTE, 0, false, false));
    }

    @Test
    void renovacoesNegativas() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.podeRenovar(
                        TipoUsuario.ALUNO, -1, false, false));
    }

    @Test
    void podeRenovarTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.podeRenovar(
                        null, 0, false, false));
    }

    @Test
    void obraComReserva() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.ALUNO, 0, true, false));
    }

    @Test
    void emprestimoEmAtraso() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.ALUNO, 0, false, true));
    }

    @Test
    void reservaEAtraso() {
        assertFalse(regras.podeRenovar(
                TipoUsuario.ALUNO, 0, true, true));
    }


    // =====================================================
    // 7. multaTotalCentavos
    // =====================================================

    @Test
    void multaTotalListaVazia() {
        assertEquals(0, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{}));
    }

    @Test
    void multaTotalListaComZero() {
        assertEquals(0, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{0}));
    }

    @Test
    void multaTotalUmDia() {
        assertEquals(100, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{1}));
    }

    @Test
    void multaTotalDoisEmprestimos() {
        assertEquals(300, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{1, 2}));
    }

    @Test
    void multaTotalProfessorTresDias() {
        assertEquals(0, regras.multaTotalCentavos(
                TipoUsuario.PROFESSOR, new int[]{3}));
    }

    @Test
    void multaTotalProfessorQuatroDias() {
        assertEquals(100, regras.multaTotalCentavos(
                TipoUsuario.PROFESSOR, new int[]{4}));
    }

    @Test
    void multaTotalVisitanteOnzeDias() {
        assertEquals(1200, regras.multaTotalCentavos(
                TipoUsuario.VISITANTE, new int[]{11}));
    }

    @Test
    void multaTotalElementoNegativoNoFinal() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(
                        TipoUsuario.ALUNO, new int[]{1, -1}));
    }

    @Test
    void multaTotalElementoNegativoNoInicio() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(
                        TipoUsuario.ALUNO, new int[]{-1, 1}));
    }

    @Test
    void multaTotalListaNula() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(
                        TipoUsuario.ALUNO, null));
    }

    @Test
    void multaTotalTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> regras.multaTotalCentavos(
                        null, new int[]{1}));
    }

    @Test
    void multaTotalAbaixoDoLimite() {
        assertEquals(9800, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{30, 29}));
    }

    @Test
    void multaTotalExatamenteNoLimite() {
        assertEquals(10000, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{30, 30}));
    }

    @Test
    void multaTotalAcimaDoLimite() {
        assertEquals(10000, regras.multaTotalCentavos(
                TipoUsuario.ALUNO, new int[]{30, 30, 1}));
    }

}
