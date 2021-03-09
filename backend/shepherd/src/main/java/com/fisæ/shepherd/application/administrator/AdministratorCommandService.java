package com.fisæ.shepherd.application.administrator;

import com.fisæ.shepherd.application.administrator.command.CreateAdministratorCommand;
import com.fisæ.shepherd.application.administrator.contracts.AdministratorDto;
import com.fisæ.shepherd.application.media.MediaService;

/**
 * Define a service able to handle the write-only operations (commands) performed on administrators
 *
 * @see MediaService
 */
public interface AdministratorCommandService {

    /**
     * Create a new administrator
     *
     * @param command Payload containing the details to create the administrator
     *
     * @return The newly created administrator
     */
    AdministratorDto create(CreateAdministratorCommand command);

}
