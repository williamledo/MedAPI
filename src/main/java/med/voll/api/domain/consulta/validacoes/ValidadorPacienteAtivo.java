package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private PacienteRepository repository;

	public void validar(DadosAgendamentoConsulta dados) {
		if (dados.idMedico() == null) {
			return;
		}

		var pacienteEstaAtivo = repository.findAtivoById(dados.idMedico());

		if (!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com médico inativo");
		}

	}
	
}