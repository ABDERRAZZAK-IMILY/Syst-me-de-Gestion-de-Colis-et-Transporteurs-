package com.logistique.gestion_colis.controller;

import com.logistique.gestion_colis.dto.TransporteurRequest;
import com.logistique.gestion_colis.models.Transporteur;
import com.logistique.gestion_colis.models.User;
import com.logistique.gestion_colis.models.enums.Specialite;
import com.logistique.gestion_colis.repository.UserRepository;
import com.logistique.gestion_colis.service.TransporteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/transporteurs")
@RequiredArgsConstructor
public class TransporteurController {

    private final TransporteurService transporteurService;
    private  final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Transporteur> createTransporteur(@RequestBody TransporteurRequest request) {
        return ResponseEntity.ok(transporteurService.createTransporteur(request));
    }

    @GetMapping
    public ResponseEntity<List<Transporteur>> getAllTransporteurs() {
        return ResponseEntity.ok(transporteurService.getAllTransporteurs());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Transporteur>> getTransporteursBySpecialite(@RequestParam Specialite specialite) {
        return ResponseEntity.ok(transporteurService.getTransporteursBySpecialite(specialite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransporteur(@PathVariable String id) {
        transporteurService.deleteTransporteur(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/admin/users/{id}/activate")
    public ResponseEntity<?> activateUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(true);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}