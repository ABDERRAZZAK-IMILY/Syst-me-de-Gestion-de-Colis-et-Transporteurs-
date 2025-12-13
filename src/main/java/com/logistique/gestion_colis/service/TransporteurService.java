package com.logistique.gestion_colis.service;

import com.logistique.gestion_colis.dto.TransporteurRequest;
import com.logistique.gestion_colis.models.Transporteur;
import com.logistique.gestion_colis.models.enums.Specialite;
import com.logistique.gestion_colis.models.enums.StatutTransporteur;
import com.logistique.gestion_colis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransporteurService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Transporteur createTransporteur(TransporteurRequest request) {
        Transporteur transporteur = new Transporteur(
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                true, // Active by default
                request.getStatut() != null ? request.getStatut() : StatutTransporteur.DISPONIBLE,
                request.getSpecialite()
        );
        return userRepository.save(transporteur);
    }

    public List<Transporteur> getAllTransporteurs() {
        return userRepository.findAll().stream()
                .filter(user -> user instanceof Transporteur)
                .map(user -> (Transporteur) user)
                .collect(Collectors.toList());
    }

    public List<Transporteur> getTransporteursBySpecialite(Specialite specialite) {
        return getAllTransporteurs().stream()
                .filter(t -> t.getSpecialite() == specialite)
                .collect(Collectors.toList());
    }

    public void deleteTransporteur(String id) {
        userRepository.deleteById(id);
    }
}