package pe.edu.upc.sellmaster.partner_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerRequest;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerResponse;
import pe.edu.upc.sellmaster.partner_service.service.PartnerService;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new partner", description = "Adds a new partner to the system")
    public void addPartner(@RequestBody PartnerRequest partnerRequest) {
        partnerService.addPartner(partnerRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all partners", description = "Retrieves a list of all partners")
    public List<PartnerResponse> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a partner by ID", description = "Retrieves a specific partner by their ID")
    public PartnerResponse getPartnerById(@PathVariable("id") long id) {
        return partnerService.getPartnerById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a partner", description = "Updates a specific partner by their ID")
    public void updatePartner(@PathVariable("id") long id, @RequestBody PartnerRequest partnerRequest) {
        partnerService.updatePartner(id, partnerRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a partner", description = "Deletes a specific partner by their ID")
    public void deletePartner(@PathVariable("id") long id) {
        partnerService.deletePartner(id);
    }

    @GetMapping("/search/by-ruc")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a partner by RUC", description = "Finds a partner by their RUC")
    public PartnerResponse findByRUC(@RequestParam long RUC) {
        return partnerService.findByRUC(RUC);
    }
}

