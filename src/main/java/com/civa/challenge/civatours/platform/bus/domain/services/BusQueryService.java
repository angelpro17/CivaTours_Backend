package com.civa.challenge.civatours.platform.bus.domain.services;

import com.civa.challenge.civatours.platform.bus.domain.model.aggregates.Bus;
import com.civa.challenge.civatours.platform.bus.domain.model.queries.GetAllBusesQuery;
import com.civa.challenge.civatours.platform.bus.domain.model.queries.GetBusByIdQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BusQueryService {
    Optional<Bus> handle(GetBusByIdQuery query);
    Page<Bus> handle(GetAllBusesQuery query, Pageable pageable);
}
