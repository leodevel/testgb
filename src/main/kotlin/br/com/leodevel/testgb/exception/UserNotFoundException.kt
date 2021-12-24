package br.com.leodevel.testgb.exception

import java.lang.RuntimeException

class UserNotFoundException: RuntimeException {

    constructor(): super()
    constructor(message: String): super(message)
    constructor(ex: Exception): super(ex)

}