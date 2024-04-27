package pe.edu.upc.sellmaster.dispatch_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.sellmaster.dispatch_service.model.dtos.DispatchDetailResponse;
import pe.edu.upc.sellmaster.dispatch_service.model.dtos.DispatchRequest;
import pe.edu.upc.sellmaster.dispatch_service.model.dtos.DispatchResponse;
import pe.edu.upc.sellmaster.dispatch_service.service.DispatchService;

import java.util.List;

@RestController
@RequestMapping("/api/dispatches")
@RequiredArgsConstructor
public class DispatchController {
    private final DispatchService dispatchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new dispatch", description = "Adds new dispatch to the system")
    public DispatchResponse addDispatch(@RequestBody DispatchRequest dispatchRequest) {
        return dispatchService.addDispatch(dispatchRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all the dispatches", description = "Get all the dispatches of the system")
    public List<DispatchDetailResponse> getAllDispatches() {
        return dispatchService.getAllDispatches();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get dispatch by ID", description = "Retrieves a specific dispatch by ID")
    public DispatchResponse getDispatchById(@PathVariable("id") long id) {
        return dispatchService.getDispatchById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update dispatch", description = "Updates a specific dispatch by ID")
    public DispatchResponse updateDispatch(@PathVariable("id") long id, @RequestBody DispatchRequest dispatchRequest) {
        return dispatchService.updateDispatch(id, dispatchRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete dispatch", description = "Deletes a specific dispatch by ID")
    public void deleteDispatch(@PathVariable("id") long id) {
        dispatchService.deleteDispatch(id);
    }
}

