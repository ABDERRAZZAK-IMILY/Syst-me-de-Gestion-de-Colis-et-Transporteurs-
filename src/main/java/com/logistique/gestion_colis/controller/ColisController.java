package com.logistique.gestion_colis.controller;

import com.logistique.gestion_colis.dto.ColisRequest;
import com.logistique.gestion_colis.dto.ColisResponse;
import com.logistique.gestion_colis.models.enums.StatutColis;
import com.logistique.gestion_colis.security.AppUserDetails;
import com.logistique.gestion_colis.service.ColisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ColisController {

    private final ColisService colisService;

    @PostMapping("/admin/colis")
    public ResponseEntity<ColisResponse> createColis(@RequestBody ColisRequest request) {
        return ResponseEntity.ok(colisService.createColis(request));
    }

    @PutMapping("/admin/colis/{id}/assign/{transporteurId}")
    public ResponseEntity<ColisResponse> assignColis(@PathVariable String id, @PathVariable String transporteurId) {
        return ResponseEntity.ok(colisService.assignColisToTransporteur(id, transporteurId));
    }

    @GetMapping("/admin/colis")
    public ResponseEntity<Page<ColisResponse>> getAllColis(
            @RequestParam(required = false) String destination,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(colisService.getAllColis(destination, PageRequest.of(page, size)));
    }

    @GetMapping("/transporteur/colis")
    public ResponseEntity<Page<ColisResponse>> getMyColis(
            @AuthenticationPrincipal AppUserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(colisService.getColisByTransporteur(userDetails.getId(), PageRequest.of(page, size)));
    }

    @PatchMapping("/transporteur/colis/{id}/status")
    public ResponseEntity<ColisResponse> updateStatut(@PathVariable String id, @RequestParam StatutColis statut) {
        return ResponseEntity.ok(colisService.updateStatut(id, statut));
    }

    @DeleteMapping("/admin/colis/{id}")
    public ResponseEntity<Void> deleteColis(@PathVariable String id) {
        colisService.deleteColis(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/colis/{id}")
    public ResponseEntity<ColisResponse> updateColis(@PathVariable String id, @RequestBody ColisRequest request) {
        return ResponseEntity.ok(colisService.updateColis(id, request));
    }
}