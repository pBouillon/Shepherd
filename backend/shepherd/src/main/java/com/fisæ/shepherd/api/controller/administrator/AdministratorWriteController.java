package com.fisæ.shepherd.api.controller.administrator;

import com.fisæ.shepherd.application.administrator.AdministratorCommandService;
import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * API controller used for the write-only operations on the administrator resource
 */
@RestController
@RequestMapping(
        path = "/api/administrators",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = AdministratorController.CONTROLLER_TAG, tags = { AdministratorController.CONTROLLER_TAG })
@Validated
public class AdministratorWriteController {

    /**
     * Service handling the read-only operations on the administrator
     */
    private final AdministratorCommandService administratorService;

    /**
     * Instantiate the controller
     *
     * @param administratorCommandService Service handling the write-only operations on the administrator
     */
    public AdministratorWriteController(AdministratorCommandService administratorCommandService) {
        administratorService = administratorCommandService;
    }

    /**
     * Endpoint for: POST /administrators
     *
     * Create a new administrator
     *
     * @param command CQRS command containing the details needed to create a new administrator
     *
     * @return A JSON payload containing the administrator created
     */
    @PostMapping
    @Operation(summary = "Create a new administrator",
            description = """
                    The administrator's nickname must not be blank or empty.
                    It also should be between 3 and 32 chars.
                    """,
            responses = {
                    @ApiResponse(responseCode = "201", description = "Administrator successfully created"),
                    @ApiResponse(responseCode = "400", description = "Malformed body")
            })
    public ResponseEntity<AdministratorDto> postAdministrator(
            @ApiParam(value = "Payload from which creating the administrator")
            @Valid @RequestBody CreateAdministratorCommand command) {
        AdministratorDto created = administratorService.create(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(created);
    }

}
