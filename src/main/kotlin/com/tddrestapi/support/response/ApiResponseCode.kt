package com.tddrestapi.support.response

enum class ApiResponseCode(val message: String) {
    SUCCESS("요청이 성공적으로 처리되었습니다."),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("이미 가입된 이메일입니다."),
    INTERNAL_SERVER_ERROR("알 수 없는 오류가 발생했습니다.")
}
