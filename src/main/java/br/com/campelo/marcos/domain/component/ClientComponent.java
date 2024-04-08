package br.com.campelo.marcos.domain.component;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.campelo.marcos.domain.entity.ClientEntity;
import br.com.campelo.marcos.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class ClientComponent {

    private final ClientRepository repository;

    public Optional<ClientEntity> findClientId(Long id) {
        return repository.findById(id);
    }
}
