package com.tailorit.unit;

import com.tailorit.domain.exception.NotFoundException;
import com.tailorit.domain.model.Cargo;
import com.tailorit.domain.model.Colaborador;
import com.tailorit.domain.repository.CargoRepository;
import com.tailorit.domain.repository.ColaboradorRepository;
import com.tailorit.domain.service.ColaboradorServiceImpl;
import com.tailorit.util.ColaboradorCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private ColaboradorServiceImpl colaboradorService;

    private final Colaborador colaborador = ColaboradorCreator.novoColaborador();

    @Test
    @DisplayName("JUnit: ColaboradorService.create(Colaborador)")
    void ColaboradorObjectSaved2() {
        // given - pré-condição ou setup
        given(colaboradorRepository.save(colaborador)).willReturn(colaborador);

        // when - ação ou comportamento a ser testado
        Colaborador colaboradorSalvo = colaboradorService.create(colaborador);

        // then - verificação das saídas
        assertThat(colaboradorSalvo).isNotNull();
    }

    @Test
    @DisplayName("Teste: ColaboradorService.create - Salva Colaborador")
    void testCreateColaborador() {
        // given
        Colaborador colaborador = new Colaborador();
        given(colaboradorRepository.save(colaborador)).willReturn(colaborador);

        // when
        Colaborador colaboradorSalvo = colaboradorService.create(colaborador);

        // then
        assertThat(colaboradorSalvo).isNotNull();
        verify(colaboradorRepository, times(1)).save(colaborador);
    }

    @Test
    @DisplayName("Teste: ColaboradorService.create - Associa Cargo")
    void testCreateColaboradorAssociatesCargo() {
        // given
        Colaborador colaborador = new Colaborador();
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        colaborador.setCargo(cargo);

        given(cargoRepository.findById(cargo.getId())).willReturn(Optional.of(cargo));
        given(colaboradorRepository.save(colaborador)).willReturn(colaborador);

        // when
        Colaborador colaboradorSalvo = colaboradorService.create(colaborador);

        // then
        assertThat(colaboradorSalvo).isNotNull();
        assertThat(colaboradorSalvo.getCargo()).isEqualTo(cargo);
        verify(colaboradorRepository, times(1)).save(colaborador);
        verify(cargoRepository, times(1)).findById(cargo.getId());
    }


    @Test
    @DisplayName("JUnit: ColaboradorService.findById(Long) - Registro Encontrado")
    void Colaborador() {
        // given - pré-condição ou setup
        given(colaboradorRepository.findById(colaborador.getId())).willReturn(Optional.of(colaborador));
        Colaborador result = colaboradorService.findById(colaborador.getId());

        // when - then - verificação das saídas
        assertNotNull(result);
        assertEquals(colaborador, result);
    }

    @Test
    @DisplayName("JUnit: ColaboradorService.findById(Long) - Registro não Encontrado - Lança NotFoundException")
    void ColaboradorId_whenFindById_thenThrowNotFoundException() {
        // when - ação ou comportamento a ser testado
        given(colaboradorRepository.findById(colaborador.getId())).willReturn(Optional.empty());

        // when-then - comportamento a ser testado e verificação da exceção
        assertThrows(NotFoundException.class, () -> colaboradorService.findById(colaborador.getId()));
    }

    @Test
    @DisplayName("JUnit: ColaboradorService.findById(Long) - Registro não Encontrado - Mensagem de Exceção Correta")
    void ColaboradorId_whenFindById_thenCheckExceptionMessage() {
        // when - ação ou comportamento a ser testado
        Long id = 1L;
        given(colaboradorRepository.findById(id)).willReturn(Optional.empty());

        // when-then - comportamento a ser testado e verificação da exceção
        NotFoundException exception = assertThrows(NotFoundException.class, () -> colaboradorService.findById(id));
        assertThat(exception.getMessage()).contains("Colaborador não encontrada.");
    }

    @Test
    @DisplayName("JUnit: ColaboradorService.update(Colaborador, colaborador)")
    void ColaboradorIsUpdated() {
        // given - pré-condição ou setup
        given(colaboradorRepository.findById(colaborador.getId())).willReturn(Optional.of(colaborador));
        given(colaboradorRepository.save(colaborador)).willReturn(colaborador);

        // when - ação ou comportamento a ser testado
        Colaborador colaboradorAtualizada = colaboradorService.update(1L, colaborador);

        // then - verificação das saídas
        assertThat(colaboradorAtualizada.getNome()).isEqualTo("nomeTeste");
    }

    @Test
    @DisplayName("JUnit: ColaboradorService.delete(Long)")
    void ColaboradorIsDeleted() {
        // given - pré-condição ou setup
        when(colaboradorRepository.findById(colaborador.getId())).thenReturn(Optional.of(colaborador));

        // when - ação ou comportamento a ser testado
        colaboradorService.delete(colaborador.getId());

        // then - verificação das saídas
        verify(colaboradorRepository).delete(colaborador);
    }

}