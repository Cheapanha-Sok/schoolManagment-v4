package com.example.schoolmangement.base.reponse

import org.springframework.stereotype.Component

@Component
class PageResponse<T>(
    var total: Long? = null,
    var results: List<T>? = null,
    var response: ResponseMessage? = ResponseMessage().response()
) {

}