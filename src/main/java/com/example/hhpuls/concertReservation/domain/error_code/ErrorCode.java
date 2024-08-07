package com.example.hhpuls.concertReservation.domain.error_code;

public enum ErrorCode {

    INVALID_TOKEN(100, "유효하지 않은 토큰입니다."),
    TOKEN_NOT_FOUND(101, "토큰 정보를 찾지 못했습니다."),
    ALREADY_TOKEN_EXPIRED(102, "이미 만료된 토큰입니다"),
    ALREADY_TOKEN_ACTIVE(103, "이미 활성화된 토큰입니다"),
    ADD_WAITING_QUEUE_FAIL(104, "대기열 등록에 실패했습니다."),
    ADD_ACTIVE_QUEUE_FAIL(105, "활성화 큐 등록에 실패했습니다."),
    CONCERT_INFO_NOT_FOUND(200, "콘서트 정보를 찾지 못했습니다."),
    CONCERT_SEAT_INFO_NOT_FOUND(201, "콘서트 좌석정보를 찾지 못했습니다."),
    PAYMENT_INFO_CREATE_ERROR(300, "결제 내역 생성에 실패했습니다."),
    PAYMENT_INFO_NOT_FOUND(301, "결제 내역을 찾지 못했습니다."),
    PAYMENT_UPDATE_DONE_STATUS_FAIL(302, "결제 완료처리에 실패했습니다."),
    PAYMENT_UPDATE_CANCEL_STATUS_FAIL(303, "결제 취소처리에 실패했습니다."),
    USER_POINT_NOT_FOUND(400, "유저 포인트 정보를 찾지 못했습니다"),
    RESERVATION_INFO_NOT_FOUND(500, "예약 정보를 찾지 못했습니다"),
    RESERVATION_UPDATE_CANCEL_STATUS_FAIL(501, "예약 취소처리에 실패했습니다"),
    RESERVATION_UPDATE_DONE_STATUS_FAIL(502, "예약 완료처리에 실패했습니다"),
    RESERVATION_SEAT_INFO_NOT_FOUND(503, "예약하려는 좌석정보를 찾지 못했습니다"),
    USER_POINT_IS_NOT_FOUND(600, "유저 포인트 정보를 찾지 못했습니다"),
    POINT_CHARGE_AMOUNT_MUST_POSITIVE_NUMBER(601, "포인트 충전량은 양수여야만 합니다."),
    POINT_USE_AMOUNT_BIGGER_THAN_RESERVE(602, "가진 양보다 많은 포인트를 사용할 수 없습니다."),
    SEAT_INFO_NOT_FOUND(700, "좌석 정보를 찾지 못했습니다.");


    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
