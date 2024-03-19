package com.example.schoolmangement.base.reponse

import org.springframework.stereotype.Component

@Component
class MessageResponse {
    var response : ResponseMessage ? = ResponseMessage().response()
}
