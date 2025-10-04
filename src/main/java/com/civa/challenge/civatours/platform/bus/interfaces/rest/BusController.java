package com.civa.challenge.civatours.platform.bus.interfaces.rest;

import com.civa.challenge.civatours.platform.bus.domain.model.queries.GetAllBusesQuery;
import com.civa.challenge.civatours.platform.bus.domain.model.queries.GetBusByIdQuery;
import com.civa.challenge.civatours.platform.bus.domain.services.BusCommandService;
import com.civa.challenge.civatours.platform.bus.domain.services.BusQueryService;
import com.civa.challenge.civatours.platform.bus.interfaces.rest.resources.BusResource;
import com.civa.challenge.civatours.platform.bus.interfaces.rest.resources.CreateBusResource;
import com.civa.challenge.civatours.platform.bus.interfaces.rest.transform.BusResourceFromEntityAssembler;
import com.civa.challenge.civatours.platform.bus.interfaces.rest.transform.CreateBusCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/bus", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bus", description = "Bus Management Endpoints")
public class BusController {
    private final BusCommandService busCommandService;
    private final BusQueryService busQueryService;

    @PostMapping
    @Operation(summary = "Create new bus", description = "Create a new bus in the system")
    public ResponseEntity<BusResource> createBus(@RequestBody CreateBusResource resource) {
        var createBusCommand = CreateBusCommandFromResourceAssembler.toCommandFromResource(resource);
        var bus = busCommandService.handle(createBusCommand);
        return bus.map(value -> new ResponseEntity<>(BusResourceFromEntityAssembler.toResourceFromEntity(value), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    @Operation(summary = "Get all buses", description = "Retrieve a paginated list of all buses. Use page and size for pagination. Sort is optional.")
    public ResponseEntity<Iterable<BusResource>> getAllBuses(
            @Parameter(description = "Page number (0-based)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field (optional)", example = "busNumber") @RequestParam(required = false) String sort) {
        
        // Create Pageable with optional sorting
        Pageable pageable;
        if (sort != null && !sort.trim().isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by(sort));
        } else {
            pageable = PageRequest.of(page, size);
        }
        
        var getAllBusesQuery = new GetAllBusesQuery();
        var buses = busQueryService.handle(getAllBusesQuery, pageable);
        var busResources = buses.map(BusResourceFromEntityAssembler::toResourceFromEntity);
        return ResponseEntity.ok(busResources);
    }

    @GetMapping("/{busId}")
    @Operation(summary = "Get bus by ID", description = "Retrieve a specific bus by its ID")
    public ResponseEntity<BusResource> getBusById(@PathVariable UUID busId) {
        var getBusByIdQuery = new GetBusByIdQuery(busId);
        var bus = busQueryService.handle(getBusByIdQuery);
        return bus.map(value -> ResponseEntity.ok(BusResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
