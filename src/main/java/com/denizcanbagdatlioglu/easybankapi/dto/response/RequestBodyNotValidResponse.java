package com.denizcanbagdatlioglu.easybankapi.dto.response;

import java.util.Map;

public record RequestBodyNotValidResponse(
        Map<String, String> fieldErrors
) {
}
