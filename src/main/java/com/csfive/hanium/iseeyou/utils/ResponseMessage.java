package com.csfive.hanium.iseeyou.utils;

import lombok.Builder;

public class ResponseMessage {
    public static String READ_USER = "회원 정보 조회 성공";
    public static String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static String CREATE_USER = "회원 가입 성공";
    public static String UPDATE_USER = "회원 정보 수정 성공";
    public static String DELETE_USER = "회원 탈퇴 성공";

    public static String CREATE_DATA = "데이터 수집 성공";

    public static String CREATE_ID = "아이디 사용 가능";
    public static String NOT_CREATE_ID = "아이디 중복";

    public static String INTERNAL_SERVER_ERROR = "서버 내부 에러";

    public static String DB_ERROR = "데이터베이스 에러";

    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";

    public static String NO_CONTENT_FILE = "파일이 없습니다.";
    public static String CONTENT_FILE = "파일이 있습니다.";

    public static String SAVE_SUCCESS ="저장 성공";
    public static String UDATE_SUCCESS = "업데이트 성공";

    public static String REGISTER_SUCCESS = "등록 성공";
    public static String REGISTER_FAIL = "등록 실패";

    public static String DELETE_FAIL = "삭제 실패";

    public static String FIND_CATEGORY = "카테고리를 찾았습니다";
    public static String FAIL_CATEGORY = "카테고리를 찾지못했습니다";

    public static String FIND_USER = "사용자를 찾았습니다";
    public static String FAIL_USER = "사용자를 찾지못했습니다";
}
