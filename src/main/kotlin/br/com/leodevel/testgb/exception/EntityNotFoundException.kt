package br.com.leodevel.testgb.exception

import java.lang.RuntimeException

class EntityNotFoundException: RuntimeException {

    constructor(message: String): super(message)
    constructor(ex: Exception): super(ex)

}